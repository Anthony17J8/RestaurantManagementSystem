package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.DishDto;

import java.util.List;

public interface DishService {

    DishDto save(DishDto dish);

    List<DishDto> findAllByMenuId(Long menuId);

    DishDto findById(Long id);

    void deleteById(Long id);
}
