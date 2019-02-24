package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> getRestaurants();

    Restaurant getRestaurant(int restaurantId);

    void saveRestaurant(Restaurant restaurant);

    void deleteRestaurant(Restaurant restaurant);
}
