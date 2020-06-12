package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Review;

public interface ReviewDAO extends GenericDAO<Review, Long> {

    Iterable<Review> findAllByRestaurantId(Long restaurantId);
}
