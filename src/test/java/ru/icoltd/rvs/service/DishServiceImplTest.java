package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.DishDAO;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.util.MockDataUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class DishServiceImplTest {

    @InjectMocks
    private DishServiceImpl dishService;

    @Mock
    private DishDAO dao;

    private Dish mockDish;

    @BeforeEach
    void setUp() {
        mockDish = withId(getMockDish());
    }

    @Test
    void testSaveDish() {
        dishService.save(mockDish);
        verify(dao).makePersistent(eq(mockDish));
    }

    @Test
    void testGetDish() {
        dishService.deleteById(mockDish.getId());
        verify(dao).deleteById(mockDish.getId());
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