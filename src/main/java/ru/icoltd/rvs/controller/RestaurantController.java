package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/showAll")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurant-list";
    }

    @GetMapping("/showFormForAdd")
    public String showAddRestaurantForm(Model model) {
        Restaurant restaurant = new Restaurant();
        RestaurantDetail rDetail = new RestaurantDetail();
        model.addAttribute("detail", rDetail);
        model.addAttribute("restaurant", restaurant);
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
        restaurantService.save(restaurant);
        return "redirect:/restaurant/showAll";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long restaurantId) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        restaurantService.remove(restaurant);
        return "redirect:/restaurant/showAll";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long restaurantId, Model model) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("detail", restaurant.getRestaurantDetail());
        return "restaurant-form";
    }
}
