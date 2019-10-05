package ru.icoltd.rvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.ReviewService;
import ru.icoltd.rvs.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, RestaurantService restaurantService, UserService userService) {
        this.reviewService = reviewService;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute("newReview") @Valid Review review, BindingResult bindingResult,
                             @RequestParam("restId") int restaurantId, Principal principal) {
        User currentUser = (User) userService.loadUserByUsername(principal.getName());
        review.setRestaurant(restaurantService.getRestaurant(restaurantId));
        review.setUser(currentUser);
        // refactoring maybe
        review.setCreatedAt(LocalDateTime.now());
        reviewService.saveReview(review);
        return "redirect:/restaurant/reviews?restId=" + restaurantId;
    }
}
