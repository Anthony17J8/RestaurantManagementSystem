package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.RestaurantMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDAO restaurantDAO;

    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDto> findAll() {
        return StreamSupport.stream(restaurantDAO.findAll().spliterator(), false)
                .map(restaurantMapper::fromRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDto findById(Long restaurantId) {
        return restaurantMapper.fromRestaurant(
                restaurantDAO.findById(restaurantId).orElseThrow(
                        () -> new ObjNotFoundException("Restaurant not found with id: " + restaurantId)
                )
        );
    }

    @Override
    @Transactional
    public RestaurantDto save(RestaurantDto restaurant) {
        return restaurantMapper.fromRestaurant(
                restaurantDAO.makePersistent(restaurantMapper.toRestaurant(restaurant))
        );
    }

    @Override
    @Transactional
    public void remove(RestaurantDto restaurant) {
        restaurantDAO.remove(restaurantMapper.toRestaurant(restaurant));
    }
}
