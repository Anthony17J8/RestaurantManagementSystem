package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    private RestaurantService restaurantService;

    private VoteDAO voteDAO;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    @GetMapping("/{menuId}/showDetails")
    public String showMenuDetails(@PathVariable("menuId") int menuId, Model model) {

        Menu menu = menuService.getMenu(menuId);
        List<Dish> dishes = menu.getDishes().stream()
                .sorted(Comparator.comparing(Dish::getName))
                .collect(Collectors.toList());

        model.addAttribute("dishes", dishes);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurant", menu.getRestaurant());

        return "menu-details";
    }

    @PostMapping("/{menuId}/addVote")
    public String voteForMenu(@ModelAttribute("menu") Menu menu) {

        Vote vote = new Vote(menu, LocalDateTime.now());
        voteDAO.saveVote(vote);

        return "redirect:/menu/{menuId}/showDetails";
    }

    @PostMapping("/addMenu")
    public String addMenu(@ModelAttribute("menu") Menu menu, @RequestParam("restId") int restaurantId) {

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.saveMenu(menu);

        return "redirect:/restaurant/" + restaurantId + "/menus";
    }

    @GetMapping("/delete")
    public String deleteMenu(@RequestParam("menuId") int menuId) {

        Menu menu = menuService.getMenu(menuId);
        menuService.deleteMenu(menu);
        return "redirect:/restaurant/" + menu.getRestaurant().getId() + "/menus";

    }
}
