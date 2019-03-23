package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Dish;

public interface DishService {

    void saveDish(Dish dish);

    Dish getDish(int dishId);

    void deleteDish(Dish dish);
}
