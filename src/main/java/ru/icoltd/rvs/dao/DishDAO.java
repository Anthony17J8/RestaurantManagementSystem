package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Dish;

import java.util.List;

public interface DishDAO {

    void saveDish(Dish dish);

    Dish getDish(int dishId);

    void deleteDish(Dish dish);

    List<Dish> getDishListByMenuId(int menuId);
}
