package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Review;

import java.util.List;

public interface ReviewDAO {

    List<Review> findAllByRestaurantId(int restaurantId);
}
