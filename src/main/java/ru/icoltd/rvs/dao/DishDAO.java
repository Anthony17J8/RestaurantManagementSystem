package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Dish;

public interface DishDAO {

    void saveDish(Dish dish);

    Dish getDish(int dishId);

    void deleteDish(Dish dish);
}
