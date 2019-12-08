package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.VoteService;
import ru.icoltd.rvs.user.CurrentUser;
import ru.icoltd.rvs.util.DateTimeUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    private VoteService voteService;

    private RestaurantService restaurantService;

    private MessageSource messageSource;

    private DishService dishService;

    @Autowired
    public MenuController(MenuService menuService, VoteService voteService, RestaurantService restaurantService,
                          MessageSource messageSource, DishService dishService) {
        this.menuService = menuService;
        this.voteService = voteService;
        this.restaurantService = restaurantService;
        this.messageSource = messageSource;
        this.dishService = dishService;
    }

    @GetMapping("/showDetails")
    public String showMenuDetails(Model model, @RequestParam("menuId") int menuId) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        return "menu-details";
    }

    @PostMapping("/addVote")
    public String voteForMenu(Model model, @RequestParam("menuId") int menuId, @CurrentUser User currentUser) {
        LocalDateTime now = LocalDateTime.now();
        Menu menu = menuService.getMenu(menuId);
        int restaurantId = menu.getRestaurant().getId();

        if (DateTimeUtils.isNotAfter(menu.getDate(), now)) {
            voteService.saveOrUpdateVote(menu, now, currentUser);
        } else {
            model.addAttribute("restaurantId", restaurantId);
            model.addAttribute("message", messageSource.getMessage("error.vote.date",
                    new Object[]{menu.getName(), menu.getDate().toLocalDate()}, Locale.getDefault()));
            return "error-page";
        }
        return "redirect:/restaurant/menus?restId=" + restaurantId;
    }

    @GetMapping("/showFormForAdd")
    public String showAddMenuForm(@RequestParam("restId") int restaurantId, Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantId", restaurantId);
        return "menu-form";
    }

    @PostMapping("/save")
    public String saveMenu(@RequestParam("restId") int restaurantId,
                           @Valid @ModelAttribute("menu") Menu menu,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurantId", restaurantId);
            // todo need to refactor
            if (!isNew(menu)) {
                setDishesFor(menu);
            }
            model.addAttribute("menu", menu);
            log.error("Save menu error {}", bindingResult);
            return "menu-form";
        }
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.saveMenu(menu);
        return "redirect:/restaurant/menus?restId=" + restaurant.getId();
    }

    private boolean isNew(Menu menu) {
        return menu.getId() == null;
    }

    private void setDishesFor(Menu menu) {
        Optional.of(menu).ifPresent(m -> m.setDishes(dishService.getDishListByMenuId(m.getId())));
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
        model.addAttribute("restaurantId", menu.getRestaurant().getId());
        return "menu-form";
    }

    @GetMapping("/toplist")
    public String filterMenus(WebRequest request, Model model) {
        LocalDateTime startDate = DateTimeUtils.parseStartLocalDateTime(request.getParameter("startDate"));
        LocalDateTime endDate = DateTimeUtils.parseEndLocalDateTime(request.getParameter("endDate"));
        List<Menu> menus = menuService.getBetweenDates(startDate, endDate);
        menus.sort(Comparator.comparing(Menu::getDate).thenComparingLong(Menu::getVotesAmount).reversed());
        model.addAttribute("menus", menus);
        return "top-list";
    }
}
