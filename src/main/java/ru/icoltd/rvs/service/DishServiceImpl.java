package ru.icoltd.rvs.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.DishDAO;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishDAO dao;

    @Override
    @Transactional
    public List<Dish> findAllByMenuId(Long menuId) {
        return Lists.newArrayList(dao.findAllInMenu(menuId));
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        return dao.makePersistent(dish);
    }

    @Override
    public Dish findById(Long id) {
        return dao.findById(id).orElseThrow(
                () -> new ObjNotFoundException("Dish with id is not found: " + id)
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
