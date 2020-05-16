package ru.icoltd.rvs.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.icoltd.rvs.dtos.DishDto;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.icoltd.rvs.controller.DishController.DISH_BASE_PATH;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @InjectMocks
    private DishController controller;

    @Mock
    private DishService dishService;

    @Mock
    private MenuService menuService;

    private DishDto mockDish;

    private MenuDto mockMenu;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockDish = getMockDishDto();
        mockMenu = getMockMenuDto();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testSaveDish() throws Exception {
        ArgumentCaptor<DishDto> dishCaptor = ArgumentCaptor.forClass(DishDto.class);
        when(menuService.findById(anyLong())).thenReturn(mockMenu);

        mockMvc.perform(post(DISH_BASE_PATH, ID, mockMenu.getId())
                .param("description", "desc")
                .param("price", "10.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate(DISH_BASE_PATH, ID, mockMenu.getId()));

        verify(dishService).save(dishCaptor.capture());

        DishDto value = dishCaptor.getValue();
        assertNotNull(value);
        assertNotNull(value.getDescription());
        assertNotNull(value.getMenu());
    }

    @Test
    void testSaveDishHasErrors() throws Exception {
        when(menuService.findById(anyLong())).thenReturn(mockMenu);

        mockMvc.perform(post(DISH_BASE_PATH, ID, mockMenu.getId())
                .param("price", "-2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-new"))
                .andExpect(model().attribute("menu", mockMenu))
                .andExpect(model().attributeExists("dish"))
                .andExpect(model().attributeHasFieldErrors("dish", "description", "price"));

        verifyZeroInteractions(dishService);
        verifyZeroInteractions(menuService);
    }

    @Test
    void testUpdateDish() throws Exception {
        mockDish.setMenu(mockMenu);

        when(menuService.findById(anyLong())).thenReturn(mockMenu);
        when(dishService.findById(ID)).thenReturn(mockDish);

        mockMvc.perform(get(DISH_BASE_PATH + "/{id}/update", ID, mockMenu.getId(), ID))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-new"))
                .andExpect(model().attribute("dish", mockDish))
                .andExpect(model().attribute("menu", mockMenu));
    }

    @Test
    void testDeleteDish() throws Exception {
        mockDish.setMenu(mockMenu);

        when(menuService.findById(anyLong())).thenReturn(mockMenu);

        mockMvc.perform(get(DISH_BASE_PATH + "/{id}/delete", ID, mockMenu.getId(), mockDish.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate(DISH_BASE_PATH, ID, mockMenu.getId()));

        verify(dishService).deleteById(mockDish.getId());
    }
}