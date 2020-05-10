package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public String listRestaurants(Model model) {
        List<RestaurantDto> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/new")
    public String showAddRestaurantForm(Model model) {
        model.addAttribute("restaurant", new RestaurantDto());
        return "restaurant-new";
    }

    @PostMapping
    public String addRestaurant(@ModelAttribute("restaurant") @Valid RestaurantDto restaurant,
                                BindingResult restBindingResult) {

        if (restBindingResult.hasErrors()) {
            log.error("Validation errors {}", restBindingResult);
            return "restaurant-new";
        }

        restaurantService.save(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long restaurantId) {
        RestaurantDto restaurant = restaurantService.findById(restaurantId);
        restaurantService.remove(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long restaurantId, Model model) {
        RestaurantDto restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        return "restaurant-new";
    }
}
