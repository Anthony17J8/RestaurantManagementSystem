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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.ID;
import static ru.icoltd.rvs.util.MockDataUtils.getMockDish;
import static ru.icoltd.rvs.util.MockDataUtils.withId;

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
        dishService.saveDish(mockDish);

        verify(dao).saveDish(eq(mockDish));
    }

    @Test
    void testGetDish() {
        when(dao.getDish(ID)).thenReturn(mockDish);

        dishService.getDish(ID);

        verify(dao).getDish(ID);
    }

    @Test
    void testGetDishNotFound() {
        assertThrows(ObjNotFoundException.class, () -> dishService.getDish(ID), "Dish id not found: " + ID);
    }

    @Test
    void testDeleteDish() {
        dishService.deleteDish(mockDish);

        verify(dao).deleteDish(mockDish);
    }

    @Test
    void testGetDishListByMenuId() {
        when(dao.getDishListByMenuId(ID)).thenReturn(MockDataUtils.getMockDishes(3));

        dishService.getDishListByMenuId(ID);

        verify(dao).getDishListByMenuId(eq(ID));
    }
}