package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    List<RestaurantDto> findAll();

    RestaurantDto findById(Long id);

    RestaurantDto save(RestaurantDto restaurant);

    void remove(RestaurantDto restaurant);
}
