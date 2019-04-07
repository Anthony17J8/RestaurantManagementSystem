package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.DateTimeUtils;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.entity.*;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.VoteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    private RestaurantService restaurantService;

    private VoteService voteService;

    private UserDAO dao;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setVoteService(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{menuId}/showDetails")
    public String showMenuDetails(@PathVariable("menuId") int menuId, Model model) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurant", menu.getRestaurant());
        return "menu-details";
    }

    @PostMapping("/{restId}/{menuId}/addVote")
    public String voteForMenu(@ModelAttribute("menu") Menu menu, Model model,
                              @PathVariable("restId") int restId) {
        LocalDateTime now = LocalDateTime.now();

        // check now.date <= menu.getDate()
        if (now.toLocalDate().isBefore(menu.getDate())) {
            saveOrUpdateVote(menu, now);
        } else {
            model.addAttribute("restId", restId);
            model.addAttribute("message",
                    messageSource.getMessage("error.vote", new Object[] {menu.getName(), menu.getDate()}, null));
            return "error-page";
        }
        return "redirect:/restaurant/" + restId + "/menus";
    }

    private void saveOrUpdateVote(Menu menu, LocalDateTime now) {
        // get vote by userId and latest
        Vote latestVote = voteService.getLatestVoteByUserId(1);

        // if it exist:
        if (latestVote != null && DateTimeUtils.isBetween(latestVote.getDateTime(), now)) {
            latestVote.setMenu(menu);
            voteService.saveVote(latestVote);
        } else {
            voteService.saveVote(new Vote(dao.getUser(1), menu, now));
        }
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute("menu") Menu menu, @RequestParam("restId") int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.saveMenu(menu);
        return "redirect:/restaurant/" + restaurantId + "/menus";
    }

    @GetMapping("/delete")
    public String deleteMenu(@RequestParam("menuId") int menuId) {
        Menu menu = menuService.getMenu(menuId);
        int restaurantId = menu.getRestaurant().getId();
        menuService.deleteMenu(menu);
        return "redirect:/restaurant/" + restaurantId + "/menus";
    }

    @GetMapping("/update")
    public String updateMenu(@RequestParam("menuId") int menuId, Model model) {
        Menu menu = menuService.getMenu(menuId);
        model.addAttribute("menu", menu);
        model.addAttribute("totalCost", getTotalCost(new ArrayList<>(menu.getDishes())));
        model.addAttribute("restaurantId", menu.getRestaurant().getId());
        return "menu-form";
    }

    private double getTotalCost(List<Dish> dishes) {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }
}
