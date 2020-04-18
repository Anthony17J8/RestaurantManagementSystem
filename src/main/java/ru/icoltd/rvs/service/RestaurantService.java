package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void remove(Restaurant restaurant);

    Restaurant findByIdWithReviews(Long restaurantId);

}
