package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.MenuDAO;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.MenuMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @InjectMocks
    private MenuServiceImpl menuService;

    @Mock
    private MenuDAO dao;

    @Mock
    private MenuMapper mapper;

    private MenuDto mockMenu;

    @BeforeEach
    void setUp() {
        mockMenu = getMockMenuDto();
    }

    @Test
    void testGetMenu() {
        Menu returned = getMockMenu();
        when(dao.findById(anyLong())).thenReturn(Optional.of(returned));
        when(mapper.menuToMenuDto(eq(returned))).thenReturn(mockMenu);
        MenuDto menu = menuService.findById(ID);
        assertEquals(mockMenu, menu);
        verify(dao).findById(anyLong());
    }

    @Test
    void testGetMenuNotFound() {
        assertThrows(ObjNotFoundException.class, () -> menuService.findById(ID), "Menu id not found: " + ID);
        verify(dao).findById(ID);
    }

    @Test
    void testSaveMenu() {
        when(mapper.menuDtoToMenu(any(MenuDto.class))).thenReturn(Menu.builder().build());
        menuService.save(mockMenu);
        verify(dao).makePersistent(any(Menu.class));
    }

    @Test
    void testDeleteMenu() {
        menuService.removeById(ID);
        verify(dao).removeById(ID);
    }

    @Test
    void testFindAllByRestaurantId() {
        menuService.findAllByRestaurantId(ID);
        verify(dao).findAllByRestaurantId(anyLong());
    }
}