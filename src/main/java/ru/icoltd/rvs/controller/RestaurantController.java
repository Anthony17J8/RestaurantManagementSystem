package ru.icoltd.rvs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
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
        return "restaurant-list";
    }

    @GetMapping("/menus")
    public String listMenus(@RequestParam(name = "restId") int restaurantId, Model model) {
        Restaurant restaurant = service.getRestaurant(restaurantId);
        model.addAttribute("restaurant", restaurant);
        List<Menu> menus = restaurant.getMenus();
        model.addAttribute("menus", menus);
        return "menu-list";
    }

    @GetMapping("/showFormForAdd")
    public String showAddRestaurantForm(ModelMap model) {
        Restaurant theRestaurant = new Restaurant();
        RestaurantDetail theDetail = new RestaurantDetail();
        model.addAttribute("detail", theDetail);
        model.addAttribute("restaurant", theRestaurant);
        return "restaurant-form";
    }

    @PostMapping("/save")
    public String addRestaurant(@ModelAttribute("restaurant") @Valid Restaurant restaurant,
                                BindingResult restBindingResult,
                                @ModelAttribute("detail") @Valid RestaurantDetail detail,
                                BindingResult detailBindingResult) {

        if (restBindingResult.hasErrors() || detailBindingResult.hasErrors()) {
            log.error("Detail {}", detailBindingResult);
            return "restaurant-form";
        }

        restaurant.setRestaurantDetail(detail);
        service.saveRestaurant(restaurant);
        return "redirect:/restaurant/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("restId") int restaurantId) {
        Restaurant restaurant = service.getRestaurant(restaurantId);
        service.deleteRestaurant(restaurant);
        return "redirect:/restaurant/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("restId") int restaurantId, Model model) {
        Restaurant restaurant = service.getRestaurant(restaurantId);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("detail", restaurant.getRestaurantDetail());
        return "restaurant-form";
    }
}
