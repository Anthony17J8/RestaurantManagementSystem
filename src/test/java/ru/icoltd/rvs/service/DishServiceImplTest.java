package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.DishDAO;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.DishMapper;
import ru.icoltd.rvs.util.MockDataUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.ID;
import static ru.icoltd.rvs.util.MockDataUtils.getMockDishDto;

@ExtendWith(MockitoExtension.class)
class DishServiceImplTest {

    @InjectMocks
    private DishServiceImpl dishService;

    @Mock
    private DishDAO dao;

    @Mock
    private DishMapper mapper;

    private DishDto mockDish;

    @BeforeEach
    void setUp() {
        mockDish = getMockDishDto();
    }

    @Test
    void testSaveDish() {
        when(mapper.toDish(mockDish)).thenReturn(Dish.builder().build());
        dishService.save(mockDish);
        verify(dao).makePersistent(any(Dish.class));
    }

    @Test
    void testGetDish() {
        when(dao.findById(anyLong())).thenReturn(Optional.of(Dish.builder().build()));
        dishService.findById(mockDish.getId());
        verify(dao).findById(mockDish.getId());
    }

    @Test
    void testGetDishNotFound() {
        assertThrows(ObjNotFoundException.class, () -> dishService.findById(ID), "Dish id not found: " + ID);
    }

    @Test
    void testDeleteDish() {
        dishService.deleteById(mockDish.getId());
        verify(dao).deleteById(mockDish.getId());
    }

    @Test
    void testGetDishListByMenuId() {
        when(dao.findAllInMenu(anyLong())).thenReturn(MockDataUtils.getMockDishes(3));
        dishService.findAllByMenuId(ID);
        verify(dao).findAllInMenu(eq(ID));
    }
}