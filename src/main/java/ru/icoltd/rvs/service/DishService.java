package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Dish;

import java.util.List;

public interface DishService {

    Dish save(Dish dish);

    List<Dish> findAllByMenuId(Long menuId);

    Dish findById(Long id);

    void deleteById(Long id);
}
