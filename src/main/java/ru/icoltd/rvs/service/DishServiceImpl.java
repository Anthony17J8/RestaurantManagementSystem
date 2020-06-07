package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.DishDAO;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.DishMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishDAO dao;

    private final DishMapper mapper;

    @Override
    @Transactional
    public List<DishDto> findAllByMenuId(Long menuId) {
        return StreamSupport.stream(dao.findAllInMenu(menuId).spliterator(), false)
                .map(mapper::fromDish)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DishDto save(DishDto dish) {
        return mapper.fromDish(dao.makePersistent(mapper.toDish(dish)));
    }

    @Override
    public DishDto findById(Long id) {
        return mapper.fromDish(dao.findById(id).orElseThrow(
                () -> new ObjNotFoundException("Dish with id is not found: " + id)
        ));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
