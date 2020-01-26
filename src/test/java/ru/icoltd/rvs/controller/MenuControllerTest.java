package ru.icoltd.rvs.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.formatters.DateTimeFormatters;
import ru.icoltd.rvs.service.DishService;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.VoteService;
import ru.icoltd.rvs.util.MockDataUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import static ru.icoltd.rvs.util.MockDataUtils.getMockDishes;
import static ru.icoltd.rvs.util.MockDataUtils.getMockMenu;
import static ru.icoltd.rvs.util.MockDataUtils.getMockMenus;
import static ru.icoltd.rvs.util.MockDataUtils.getMockRestaurant;
import static ru.icoltd.rvs.util.MockDataUtils.withId;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    @InjectMocks
    private MenuController controller;

    @Mock
    private MenuService menuService;

    @Mock
    private VoteService voteService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private DishService dishService;

    @Mock
    private MessageSource messageSource;

    @Captor
    private ArgumentCaptor<Menu> menuCaptor;

    private Menu mockMenu;

    private Restaurant mockRestaurant;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addFormatter(new DateTimeFormatters.LocalDateFormatter());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setConversionService(conversionService).build();
        mockMenu = withId(getMockMenu());
        mockRestaurant = withId(getMockRestaurant());
    }

    @Test
    void testShowMenuDetails() throws Exception {
        mockMenu.setDishes(MockDataUtils.getMockDishes(2));
        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);
        mockMvc.perform(get("/menu/showDetails?menuId={id}", mockMenu.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-details"))
                .andExpect(model().attribute("menu", mockMenu));
    }

    @Test
    void testVoteForMenuPastDate() throws Exception {
        LocalDate past = LocalDate.now().minusDays(2);
        mockMenu.setRestaurant(mockRestaurant);
        mockMenu.setDate(past);

        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);
        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("error occurred");

        mockMvc.perform(post("/menu/addVote?menuId={id}", mockMenu.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("error-page"))
                .andExpect(model().attribute("restaurantId", mockRestaurant.getId()))
                .andExpect(model().attributeExists("message"));

        verifyZeroInteractions(voteService);
    }

    @Test
    void testVoteForMenu() throws Exception {
        LocalDate past = LocalDate.now().plusDays(2);
        mockMenu.setRestaurant(mockRestaurant);
        mockMenu.setDate(past);

        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);

        mockMvc.perform(post("/menu/addVote?menuId={id}", mockMenu.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant/menus?restId=" + mockRestaurant.getId()));

        verify(voteService).saveOrUpdateVote(eq(mockMenu), any(LocalDateTime.class), any(User.class));
    }

    @Test
    void testShowAddMenuForm() throws Exception {
        mockMvc.perform(get("/menu/showFormForAdd?restId={id}", mockRestaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-form"))
                .andExpect(model().attribute("menu", Matchers.notNullValue(Menu.class)))
                .andExpect(model().attribute("restaurantId", mockRestaurant.getId()));
    }

    @Test
    void testSaveMenu() throws Exception {

        when(restaurantService.getRestaurant(anyInt())).thenReturn(mockRestaurant);

        mockMvc.perform(post("/menu/save?restId={id}", mockRestaurant.getId())
                .param("name", "new menu")
                .param("date", "2019-05-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant/menus?restId=" + mockRestaurant.getId()));

        verify(menuService).saveMenu(menuCaptor.capture());

        Menu savedMenu = menuCaptor.getValue();

        assertEquals(mockRestaurant, savedMenu.getRestaurant());
    }

    @Test
    void testSaveMenuHasError() throws Exception {

        mockMvc.perform(post("/menu/save?restId={id}", mockRestaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-form"))
                .andExpect(model().attribute("restaurantId", mockRestaurant.getId()))
                .andExpect(model().attribute("menu", Matchers.notNullValue(Menu.class)))
                .andExpect(model().attributeHasFieldErrors("menu", "name", "date"));
    }

    @Test
    void testSaveMenuWhenUpdate() throws Exception {
        List<Dish> dishes = getMockDishes(3);

        when(dishService.getDishListByMenuId(anyInt())).thenReturn(dishes);

        mockMvc.perform(post("/menu/save?restId={id}", mockRestaurant.getId())
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-form"))
                .andExpect(model().attribute("menu",
                        Matchers.hasProperty("dishes", Matchers.hasSize(3))))
                .andExpect(model().attribute("restaurantId", mockRestaurant.getId()));
    }

    @Test
    void testDeleteMenu() throws Exception {
        mockMenu.setRestaurant(mockRestaurant);

        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);
        mockMvc.perform(get("/menu/delete?menuId={id}", mockMenu.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant/menus?restId=" + mockRestaurant.getId()));

        verify(menuService).deleteMenu(eq(mockMenu));
    }

    @Test
    void testUpdateMenu() throws Exception {
        mockMenu.setRestaurant(mockRestaurant);
        mockMenu.setDishes(getMockDishes(2));

        when(menuService.getMenu(anyInt())).thenReturn(mockMenu);
        mockMvc.perform(get("/menu/update?menuId={id}", mockMenu.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-form"))
                .andExpect(model().attribute("menu", mockMenu))
                .andExpect(model().attribute("restaurantId", mockRestaurant.getId()));
    }

    @Test
    void testFilterMenus() throws Exception {
        List<Menu> menus = getMockMenus(5);
        when(menuService.getBetweenDates(any(LocalDate.class), any(LocalDate.class))).thenReturn(menus);
        mockMvc.perform(get("/menu/toplist")
                .param("startDate", "2019-05-05")
                .param("endDate", "2019-05-30"))
                .andExpect(status().isOk())
                .andExpect(view().name("top-list"))
                .andExpect(model().attribute("menus", menus));
    }
}