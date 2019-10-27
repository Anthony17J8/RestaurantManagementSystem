package ru.icoltd.rvs.controller;

import org.hamcrest.Matchers;
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
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.icoltd.rvs.util.MockDataUtils.ID;
import static ru.icoltd.rvs.util.MockDataUtils.getMockDish;
import static ru.icoltd.rvs.util.MockDataUtils.getMockMenu;
import static ru.icoltd.rvs.util.MockDataUtils.withId;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @InjectMocks
    private DishController controller;

    @Mock
    private DishService dishService;

    @Mock
    private MenuService menuService;

    private Dish mockDish;

    private Menu mockMenu;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockDish = withId(getMockDish());
        mockMenu = withId(getMockMenu());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testShowFormForAdd() throws Exception {
        when(menuService.getMenu(ID)).thenReturn(mockMenu);

        mockMvc.perform(get("/dish/showFormForAdd?menuId={id}", ID))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-form"))
                .andExpect(model().attribute("dish", Matchers.notNullValue(Dish.class)))
                .andExpect(model().attribute("menuId", mockMenu.getId()));
    }

    @Test
    void testSaveDish() throws Exception {
        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);

        mockMvc.perform(post("/dish/save?menuId={id}", mockMenu.getId())
                .param("description", "desc")
                .param("price", "10.0")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/update?menuId=" + mockMenu.getId()));

        verify(dishService).saveDish(dishCaptor.capture());

        Dish value = dishCaptor.getValue();
        assertNotNull(value);
        assertEquals(mockMenu, value.getMenu());
    }

    @Test
    void testSaveDishHasErrors() throws Exception {
        mockMvc.perform(post("/dish/save?menuId={id}", mockMenu.getId())
                .param("price", "-2")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-form"))
                .andExpect(model().attribute("menuId", mockMenu.getId()))
                .andExpect(model().attributeExists("dish"))
                .andExpect(model().attributeHasFieldErrors("dish", "description", "price"));

        verifyZeroInteractions(dishService);
        verifyZeroInteractions(menuService);
    }

    @Test
    void testUpdateDish() throws Exception {
        mockDish.setMenu(mockMenu);
        when(dishService.getDish(ID)).thenReturn(mockDish);

        mockMvc.perform(get("/dish/update?dishId={id}", ID))
                .andExpect(status().isOk())
                .andExpect(view().name("dish-form"))
                .andExpect(model().attribute("dish", mockDish))
                .andExpect(model().attribute("menuId", mockMenu.getId()));
    }

    @Test
    void testDeleteDish() throws Exception {
        mockDish.setMenu(mockMenu);
        when(dishService.getDish(ID)).thenReturn(mockDish);

        mockMvc.perform(get("/dish/delete?dishId={id}", ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/update?menuId=" + mockMenu.getId()));

        verify(dishService).deleteDish(eq(mockDish));
    }
}