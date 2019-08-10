package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Dish;

import java.util.List;

public interface DishService {

    void saveDish(Dish dish);

    Dish getDish(int dishId);

    void deleteDish(Dish dish);

    List<Dish> getDishListByMenuId(int menuId);
}
