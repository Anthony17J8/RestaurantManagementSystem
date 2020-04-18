package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Menu;

public interface MenuDAO extends GenericDAO<Menu, Long> {

    Iterable<Menu> findAllByRestaurantId(Long restaurantId);

    void removeById(Long id);
}
