package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.icoltd.rvs.DateTimeUtils;
import ru.icoltd.rvs.entity.*;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.UserService;
import ru.icoltd.rvs.service.VoteService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j
@SessionAttributes(value = {"menu", "restaurantId"})
public class MenuController {

    private MenuService menuService;

    private VoteService voteService;

    private RestaurantService restaurantService;

    private UserService userService;

    private MessageSource messageSource;

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

    @GetMapping("/showDetails")
    public String showMenuDetails(Model model, @RequestParam("menuId") int menuId) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantId", menu.getRestaurant().getId());
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
            model.addAttribute("message",
                    messageSource.getMessage("error.vote.date", new Object[]{menu.getName(), menu.getDate()}, null));
            return "error-page";
        }
        return "redirect:/restaurant/menus?restId=" + restaurantId;
    }

    private void saveOrUpdateVote(Menu menu, LocalDateTime now, Principal principal) {
        User existUser = userService.findUserByUserName(principal.getName());

        Vote latestVote = voteService.getLatestVoteByUserId(existUser.getId());

        // if it exist:
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
                           BindingResult bindingResult, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            log.error("Save menu error {}", bindingResult);
            return "menu-form";
        }
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.saveMenu(menu);
        sessionStatus.setComplete();
        return "redirect:/restaurant/menus?restId=" + restaurant.getId();
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

    private double getTotalAmount(List<Dish> dishes) {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }
}
