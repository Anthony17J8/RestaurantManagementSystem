package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.ReviewDto;
import ru.icoltd.rvs.entity.Review;

import java.util.List;

public interface ReviewService {

    Review save(Review review);

    List<ReviewDto> findAllByRestaurantId(Long restaurantId);
}
