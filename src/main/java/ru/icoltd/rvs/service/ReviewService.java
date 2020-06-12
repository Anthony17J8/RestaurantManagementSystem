package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.ReviewDto;
import ru.icoltd.rvs.entity.User;

import java.util.List;

public interface ReviewService {

    ReviewDto save(ReviewDto review, User currentUser);

    List<ReviewDto> findAllByRestaurantId(Long restaurantId);
}
