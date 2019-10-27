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

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.ID;
import static ru.icoltd.rvs.util.MockDataUtils.withId;
import static ru.icoltd.rvs.util.MockDataUtils.getMockMenu;

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
        ZonedDateTime start = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime end = start.plus(2, ChronoUnit.DAYS);

        menuService.getBetweenDates(start, end);

        verify(dao).getBetweenDates(eq(start), eq(end));
    }

    @Test
    void testGetBetweenUnboundedDates() {
        menuService.getBetweenDates(null, null);
        verify(dao).getBetweenDates(
                eq(ZonedDateTime.of(DateTimeUtils.MIN_DATE, LocalTime.MIN, DateTimeUtils.ZONE_ID_UTC)),
                eq(ZonedDateTime.of(DateTimeUtils.MAX_DATE, LocalTime.MAX, DateTimeUtils.ZONE_ID_UTC)));
    }

    @Test
    void testFindAllByRestaurantId() {
        menuService.findAllByRestaurantId(ID);
        verify(dao).findAllByRestaurantId(anyInt());
    }
}