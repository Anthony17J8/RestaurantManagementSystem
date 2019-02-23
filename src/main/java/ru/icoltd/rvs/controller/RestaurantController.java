package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.service.RestaurantService;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public void setService(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model) {

        List<Restaurant> restaurants = service.getRestaurants();
        model.addAttribute("restaurants", restaurants);

        return "resaurant-list";
    }

    @GetMapping("/{restaurantId}/showMenus")
    public String listMenus(@PathVariable(name = "restaurantId") int restaurantId, Model model) {

        Restaurant restaurant = service.getRestaurant(restaurantId);
        List<Menu> menus = restaurant.getMenus();
        model.addAttribute("menus", menus);

        return "menus";
    }

    @GetMapping("/showAddRestaurantForm")
    public String showAddRestaurantForm(ModelMap theModel) {

        Restaurant theRestaurant = new Restaurant();
        RestaurantDetail theDetail = new RestaurantDetail();
        theModel.addAttribute("detail", theDetail);
        theModel.addAttribute("restaurant", theRestaurant);

        return "restaurant-form";
    }

    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute("restaurant") Restaurant restaurant,
                                @ModelAttribute("detail") RestaurantDetail detail) {

        restaurant.setRestaurantDetail(detail);
        service.saveRestaurant(restaurant);

        return "redirect:/restaurant/list";
    }
}
