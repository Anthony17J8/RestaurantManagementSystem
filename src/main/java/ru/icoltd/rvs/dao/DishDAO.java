package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Dish;

public interface DishDAO extends GenericDAO<Dish, Long> {

    Iterable<Dish> findAllInMenu(Long menuId);

    void deleteById(Long id);
}
