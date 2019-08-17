package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.icoltd.rvs.DateTimeUtils;
import ru.icoltd.rvs.entity.*;
import ru.icoltd.rvs.service.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    private MenuService menuService;

    private VoteService voteService;

    private RestaurantService restaurantService;

    private UserService userService;

    private MessageSource messageSource;

    private DishService dishService;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/showDetails")
    public String showMenuDetails(Model model, @RequestParam("menuId") int menuId) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurant", menu.getRestaurant());
        return "menu-details";
    }

    @PostMapping("/addVote")
    public String voteForMenu(Model model, @RequestParam("menuId") int menuId, Principal principal) {
        LocalDateTime now = LocalDateTime.now();
        Menu menu = menuService.getMenu(menuId);
        int restaurantId = menu.getRestaurant().getId();

        if (DateTimeUtils.isNotBeforeNow(menu.getDate(), now)) {
            saveOrUpdateVote(menu, now, principal);
        } else {
            model.addAttribute("restaurantId", restaurantId);
            model.addAttribute("message", messageSource.getMessage("error.vote.date",
                    new Object[]{menu.getName(), menu.getDate().toLocalDate()}, null));
            return "error-page";
        }
        return "redirect:/restaurant/menus?restId=" + restaurantId;
    }

    private void saveOrUpdateVote(Menu menu, LocalDateTime now, Principal principal) {
        User existUser = userService.findUserByUserName(principal.getName());

        Vote latestVote = voteService.getLatestVoteByUserId(existUser.getId());

        if (latestVote != null && DateTimeUtils.isBetween(latestVote.getDateTime(), now)) {
            latestVote.setMenu(menu);
            voteService.saveVote(latestVote);
        } else {
            voteService.saveVote(new Vote(existUser, menu, now));
        }
    }

    @GetMapping("/showFormForAdd")
    public String showAddMenuForm(@RequestParam("restId") int restaurantId, Model model) {
        Menu menu = new Menu();
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantId", restaurant.getId());
        return "menu-form";
    }

    @PostMapping("/save")
    public String saveMenu(@RequestParam("restId") int restaurantId,
                           @Valid @ModelAttribute("menu") Menu menu,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurantId", restaurantId);
            model.addAttribute("menu", fillWithDishes(menu));
            log.error("Save menu error {}", bindingResult);
            return "menu-form";
        }
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.saveMenu(menu);
        return "redirect:/restaurant/menus?restId=" + restaurant.getId();
    }

    private Menu fillWithDishes(Menu menu) {
        menu.setDishes(dishService.getDishListByMenuId(menu.getId()));
        return menu;
    }

    @GetMapping("/delete")
    public String deleteMenu(@RequestParam("menuId") int menuId) {
        Menu menu = menuService.getMenu(menuId);
        int restaurantId = menu.getRestaurant().getId();
        menuService.deleteMenu(menu);
        return "redirect:/restaurant/menus?restId=" + restaurantId;
    }

    @GetMapping("/update")
    public String updateMenu(@RequestParam("menuId") int menuId, Model model) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("totalAmount", getTotalAmount(new ArrayList<>(menu.getDishes())));
        model.addAttribute("restaurantId", menu.getRestaurant().getId());
        return "menu-form";
    }

    @GetMapping("/toplist")
    public String filterMenus(WebRequest request, Model model) {
        ZonedDateTime startDate = DateTimeUtils.parseStartZoneDateTime(request.getParameter("startDate"));
        ZonedDateTime endDate = DateTimeUtils.parseEndZoneDateTime(request.getParameter("endDate"));
        List<Menu> menus = getBetween(startDate, endDate);
        model.addAttribute("menus", menus);
        return "top-list";
    }

    private List<Menu> getBetween(ZonedDateTime startDate, ZonedDateTime endDate) {
        return menuService.getBetweenDates(startDate, endDate);
    }

    private double getTotalAmount(List<Dish> dishes) {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }
}
