package ru.icoltd.rvs.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    @Override
    public List<Restaurant> findAll() {
        return Lists.newArrayList(restaurantDAO.findAll());
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return restaurantDAO.findById(restaurantId).orElseThrow(
                () -> new ObjNotFoundException("Restaurant not found with id: " + restaurantId)
        );
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return restaurantDAO.makePersistent(restaurant);
    }

    @Override
    @Transactional
    public void remove(Restaurant restaurant) {
        restaurantDAO.remove(restaurant);
    }

    @Override
    public Restaurant findByIdWithReviews(Long restaurantId) {
        return restaurantDAO.findByIdWithReviews(restaurantId).orElseThrow(
                () -> new ObjNotFoundException("Restaurant id not found: " + restaurantId)
        );
    }
}
