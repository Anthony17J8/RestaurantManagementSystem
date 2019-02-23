package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.entity.Restaurant;

import java.util.List;

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
        return restaurantDAO.findById(restaurantId);
    }

    @Override
    @Transactional
    public void saveRestaurant(Restaurant restaurant) {
        restaurantDAO.saveRestaurant(restaurant);
    }
}
