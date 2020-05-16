package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.MenuDto;

import java.util.List;

public interface MenuService {

    MenuDto save(MenuDto menu);

    void removeById(Long id);

    List<MenuDto> findAllByRestaurantId(Long restId);

    MenuDto findById(Long menuId);
}
