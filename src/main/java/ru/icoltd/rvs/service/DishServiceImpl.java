package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.DishDAO;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private DishDAO dao;

    @Autowired
    public void setDao(DishDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void saveDish(Dish dish) {
        dao.saveDish(dish);
    }

    @Override
    @Transactional
    public Dish getDish(int dishId) {
        return Optional.ofNullable(dao.getDish(dishId))
                .orElseThrow(
                        () -> new ObjNotFoundException("Dish id not found: " + dishId)
                );
    }

    @Override
    @Transactional
    public void deleteDish(Dish dish) {
        dao.deleteDish(dish);
    }
}
