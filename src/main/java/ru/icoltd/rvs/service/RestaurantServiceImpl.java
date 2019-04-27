package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantDAO restaurantDAO;

    @Autowired
    public void setRestaurantDAO(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @Override
    @Transactional
    public List<Restaurant> getRestaurants() {
        return restaurantDAO.getRestaurants();
    }

    @Override
    @Transactional
    public Restaurant getRestaurant(int restaurantId) {
        return Optional.ofNullable(restaurantDAO.findById(restaurantId))
                .orElseThrow(
                        () -> new ObjNotFoundException("Restaurant id not found: " + restaurantId)
                );
    }

    @Override
    @Transactional
    public void saveRestaurant(Restaurant restaurant) {
        restaurantDAO.saveRestaurant(restaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantDAO.deleteRestaurant(restaurant);
    }
}
