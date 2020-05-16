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
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.formatters.DateTimeFormatters;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.VoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class MenuControllerTest {

    private static final String MENU_BASE_PATH = "/restaurants/{restId}/menus";

    @InjectMocks
    private MenuController controller;

    @Mock
    private MenuService menuService;

    @Mock
    private VoteService voteService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private MessageSource messageSource;

    @Captor
    private ArgumentCaptor<MenuDto> menuCaptor;

    private MenuDto mockMenu;

    private RestaurantDto mockRestaurant;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addFormatter(new DateTimeFormatters.LocalDateTimeFormatter());
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setConversionService(conversionService)
                .setViewResolvers(viewResolver).build();
        mockMenu = getMockMenuDto();
        mockRestaurant = getMockRestaurantDto();
    }

    @Test
    void testShowAll() throws Exception {
        List<MenuDto> mockMenus = getMockMenuDtos(2);

        when(menuService.findAllByRestaurantId(anyLong())).thenReturn(mockMenus);
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);

        mockMvc.perform(get(MENU_BASE_PATH, ID))
                .andExpect(status().isOk())
                .andExpect(view().name("menus"))
                .andExpect(model().attribute("restaurant", mockRestaurant))
                .andExpect(model().attribute("menus", mockMenus));
    }

    @Test
    void testVoteForMenuPastDate() throws Exception {
        LocalDateTime past = LocalDateTime.now().minusDays(2);
        mockMenu.setDate(past);

        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);
        when(menuService.findById(anyLong())).thenReturn(mockMenu);
        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn("error occurred");

        mockMvc.perform(get(MENU_BASE_PATH + "/{id}/vote", mockRestaurant.getId(), mockMenu.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("error-page"))
                .andExpect(model().attribute("restaurant", mockRestaurant))
                .andExpect(model().attributeExists("message"));

        verifyZeroInteractions(voteService);
    }

    @Test
    void testVoteForMenu() throws Exception {
        LocalDateTime past = LocalDateTime.now().plusDays(2);
        mockMenu.setDate(past);

        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);
        when(menuService.findById(anyLong())).thenReturn(mockMenu);

        mockMvc.perform(get(MENU_BASE_PATH + "/{id}/vote", mockRestaurant.getId(), mockMenu.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate(MENU_BASE_PATH, mockRestaurant.getId()));

//        verify(voteService).saveOrUpdateVote(eq(mockMenu), any(LocalDateTime.class), any(User.class));
    }

    @Test
    void testShowAddMenuForm() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);

        mockMvc.perform(get(MENU_BASE_PATH + "/new", mockRestaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-new"))
                .andExpect(model().attribute("menu", Matchers.notNullValue(Menu.class)))
                .andExpect(model().attribute("restaurant", mockRestaurant));
    }

    @Test
    void testSaveMenu() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);

        mockMvc.perform(post(MENU_BASE_PATH, mockRestaurant.getId())
                .param("name", "new menu")
                .param("date", "2019-05-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate(MENU_BASE_PATH, mockRestaurant.getId()));

        verify(menuService).save(menuCaptor.capture());

        MenuDto savedMenu = menuCaptor.getValue();

        assertNotNull(savedMenu.getName());
        assertNotNull(savedMenu.getDate());
        assertNotNull(savedMenu.getRestaurant());
    }

    @Test
    void testSaveMenuHasError() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);

        mockMvc.perform(post(MENU_BASE_PATH, mockRestaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-new"))
                .andExpect(model().attribute("restaurant", mockRestaurant))
                .andExpect(model().attribute("menu", Matchers.notNullValue(Menu.class)))
                .andExpect(model().attributeHasFieldErrors("menu", "name", "date"));
    }

    @Test
    void testUpdateMenu() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);
        when(menuService.findById(anyLong())).thenReturn(mockMenu);
        mockMvc.perform(get(MENU_BASE_PATH + "/{id}/update", ID, ID))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-new"))
                .andExpect(model().attribute("menu", mockMenu))
                .andExpect(model().attribute("restaurant", mockRestaurant));
    }

    @Test
    void testDeleteMenu() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(mockRestaurant);
        mockMvc.perform(get(MENU_BASE_PATH + "/{id}/delete", mockRestaurant.getId(), mockMenu.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate(MENU_BASE_PATH, mockRestaurant.getId()));

        verify(menuService).removeById(mockMenu.getId());
    }
}