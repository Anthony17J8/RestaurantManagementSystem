package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;

import java.util.List;

public interface MenuService {

    Menu save(Menu menu);

    void removeById(Long id);

    List<Menu> findAllByRestaurantId(Long restId);

    Menu findById(Long menuId);
}
