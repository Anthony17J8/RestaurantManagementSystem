package ru.icoltd.rvs.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.ReviewService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    private RestaurantService restaurantService;

    private ReviewService reviewService;

    private MenuService menuService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ReviewService reviewService,
                                MenuService menuService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "restaurant-list";
    }

    @GetMapping("/menus")
    public String listMenus(@RequestParam(name = "restId") int restaurantId, Model model) {
        List<Menu> menus = menuService.findAllByRestaurantId(restaurantId);
        Restaurant restaurant = menus.stream()
                .findFirst()
                .flatMap(m -> Optional.ofNullable(m.getRestaurant()))
                .orElse(restaurantService.getRestaurant(restaurantId));
        model.addAttribute("menus", menus);
        model.addAttribute("restaurant", restaurant);
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
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("restId") int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        restaurantService.deleteRestaurant(restaurant);
        return "redirect:/restaurant/list";
    }

    @GetMapping("/update")
    public String update(@RequestParam("restId") int restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("detail", restaurant.getRestaurantDetail());
        return "restaurant-form";
    }

    @GetMapping("/reviews")
    public String showReviews(@RequestParam("restId") int restaurantId, Model model) {
        List<Review> reviews = reviewService.findAllByRestaurantId(restaurantId);
        Review newReview = new Review();
        Restaurant restaurant = reviews.stream()
                .findFirst()
                .flatMap(m -> Optional.ofNullable(m.getRestaurant()))
                .orElse(restaurantService.getRestaurant(restaurantId));
        model.addAttribute("newReview", newReview);
        model.addAttribute("reviews", reviews);
        model.addAttribute("restaurant", restaurant);
        return "reviews";
    }
}
