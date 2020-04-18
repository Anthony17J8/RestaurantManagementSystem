package ru.icoltd.rvs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.user.CurrentUser;

import javax.validation.Valid;

@Controller
@RequestMapping("/restaurant/{restId}/review")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final RestaurantService restaurantService;

    @InitBinder("restaurant")
    public void initRestaurantBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("restaurant")//todo refactor?
    public Restaurant restaurant(@PathVariable("restId") Long restId) {
        return restaurantService.findByIdWithReviews(restId);
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute("newReview") @Valid Review review,
                             BindingResult bindingResult, Restaurant restaurant,
                             @CurrentUser User currentUser) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(e -> log.error("Invalid data of Review: {} - {}", e.getField(), e.getDefaultMessage()));
            return "reviews";
        }

        review.setUser(currentUser);
        review.setRestaurant(restaurant);
        restaurant.getReviews().add(review);
        restaurantService.save(restaurant);
        return "redirect:/restaurant/{restId}/review/showAll";
    }

    @GetMapping("/showAll")
    public String showReviews(Model model) {
        Review review = new Review();
        model.addAttribute("newReview", review);
        return "reviews";
    }
}
