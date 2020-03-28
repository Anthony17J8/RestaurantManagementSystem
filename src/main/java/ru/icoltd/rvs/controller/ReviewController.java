package ru.icoltd.rvs.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.ReviewService;
import ru.icoltd.rvs.user.CurrentUser;

@Controller
@RequestMapping("/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    private final RestaurantService restaurantService;

    @Autowired
    public ReviewController(ReviewService reviewService, RestaurantService restaurantService) {
        this.reviewService = reviewService;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute("newReview") @Valid Review review,
                             BindingResult bindingResult, @CurrentUser User currentUser,
                             @RequestParam("restId") Integer restId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurant", restaurantService.getRestaurant(restId));
            model.addAttribute("reviews", reviewService.findAllByRestaurantId(restId));
            bindingResult.getFieldErrors()
                    .forEach(e -> log.error("Invalid data of Review: {} - {}", e.getField(), e.getDefaultMessage()));
            return "reviews";
        }
        review.setRestaurant(restaurantService.getRestaurant(restId));
        review.setUser(currentUser);
        reviewService.saveReview(review);
        return "redirect:/restaurant/reviews?restId=" + restId;
    }
}
