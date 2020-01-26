package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.util.DateTimeUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.ID;
import static ru.icoltd.rvs.util.MockDataUtils.getMockMenu;
import static ru.icoltd.rvs.util.MockDataUtils.withId;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @InjectMocks
    private MenuServiceImpl menuService;

    @Mock
    private MenuDAO dao;

    private Menu mockMenu;

    @BeforeEach
    void setUp() {
        mockMenu = withId(getMockMenu());
    }

    @Test
    void testGetMenu() {
        when(dao.findById(anyInt())).thenReturn(mockMenu);

        Menu menu = menuService.getMenu(ID);

        assertEquals(mockMenu, menu);

        verify(dao).findById(anyInt());
    }

    @Test
    void testGetMenuNotFound() {
        assertThrows(ObjNotFoundException.class, () -> menuService.getMenu(ID), "Menu id not found: " + ID);
        verify(dao).findById(ID);
    }

    @Test
    void testSaveMenu() {
        menuService.saveMenu(mockMenu);
        verify(dao).saveMenu(eq(mockMenu));
    }

    @Test
    void testDeleteMenu() {
        menuService.deleteMenu(mockMenu);
        verify(dao).deleteMenu(mockMenu);
    }

    @Test
    void testGetBetweenDates() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(2);

        menuService.getBetweenDates(start, end);

        verify(dao).getBetweenDates(eq(start), eq(end));
    }

    @Test
    void testGetBetweenUnboundedDates() {
        menuService.getBetweenDates(null, null);
        verify(dao).getBetweenDates(
                eq(DateTimeUtils.MIN_DATE),
                eq(DateTimeUtils.MAX_DATE));
    }

    @Test
    void testFindAllByRestaurantId() {
        menuService.findAllByRestaurantId(ID);
        verify(dao).findAllByRestaurantId(anyInt());
    }
}