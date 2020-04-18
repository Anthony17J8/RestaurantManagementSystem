package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Restaurant;

import java.util.Optional;

public interface RestaurantDAO extends GenericDAO<Restaurant, Long> {

    Optional<Restaurant> findByIdWithReviews(Long restaurantId);

    Optional<Restaurant> findByIdWithMenus(Long restaurantId);
}
