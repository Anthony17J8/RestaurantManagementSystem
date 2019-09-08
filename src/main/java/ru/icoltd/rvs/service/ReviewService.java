package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAllByRestaurantId(int restaurantId);

    void saveReview(Review review);
}
