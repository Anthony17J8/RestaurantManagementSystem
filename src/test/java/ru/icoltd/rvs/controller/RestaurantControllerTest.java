package ru.icoltd.rvs.controller;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.service.MenuService;
import ru.icoltd.rvs.service.RestaurantService;
import ru.icoltd.rvs.service.ReviewService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    private static final Integer ID = 1;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private MenuService menuService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private RestaurantController controller;

    private MockMvc mockMvc;

    private Restaurant returned;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
        returned = Restaurant.builder().id(ID).restaurantDetail(RestaurantDetail.builder().build()).build();
    }

    @Test
    void testListRestaurants() throws Exception {
        Restaurant restaurant1 = Restaurant.builder().id(1).build();
        Restaurant restaurant2 = Restaurant.builder().id(2).build();
        List<Restaurant> returnedList = Lists.newArrayList(restaurant1, restaurant2);

        when(restaurantService.getRestaurants()).thenReturn(returnedList);

        mockMvc.perform(get("/restaurant/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurants", Matchers.hasSize(2)))
                .andExpect(view().name("restaurant-list"));
    }

    @Test
    void testListMenus() throws Exception {
        Menu menu1 = Menu.builder().build();
        Menu menu2 = Menu.builder().build();
        List<Menu> menus = Lists.newArrayList(menu1, menu2);

        when(menuService.findAllByRestaurantId(anyInt())).thenReturn(menus);
        when(restaurantService.getRestaurant(anyInt())).thenReturn(returned);

        mockMvc.perform(get("/restaurant/menus?restId={id}", ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("menus", Matchers.hasSize(2)))
                .andExpect(model().attribute("restaurant", Matchers.equalTo(returned)))
                .andExpect(view().name("menu-list"));
    }

    @Test
    void testShowAddRestaurantForm() throws Exception {
        mockMvc.perform(get("/restaurant/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("detail", Matchers.notNullValue(RestaurantDetail.class)))
                .andExpect(model().attribute("restaurant", Matchers.notNullValue(Restaurant.class)))
                .andExpect(view().name("restaurant-form"));
    }

    @Test
    void testAddRestaurant() throws Exception {
        mockMvc.perform(post("/restaurant/save"))
                .andExpect(redirectedUrl("/restaurant/list"))
                .andExpect(status().isFound());
        verify(restaurantService).saveRestaurant(any(Restaurant.class));
    }

    @Test
    void testDelete() throws Exception {
        when(restaurantService.getRestaurant(anyInt())).thenReturn(returned);
        mockMvc.perform(get("/restaurant/delete?restId={id}", ID))
                .andExpect(redirectedUrl("/restaurant/list"))
                .andExpect(status().isFound());
        verify(restaurantService).deleteRestaurant(eq(returned));
    }

    @Test
    void testUpdate() throws Exception {
        when(restaurantService.getRestaurant(anyInt())).thenReturn(returned);
        mockMvc.perform(get("/restaurant/update?restId={id}", ID))
                .andExpect(model().attribute("detail", Matchers.notNullValue(RestaurantDetail.class)))
                .andExpect(model().attribute("restaurant", Matchers.notNullValue(Restaurant.class)))
                .andExpect(view().name("restaurant-form"));
    }

    @Test
    void testShowReviews() throws Exception {
        Review review1 = Review.builder().build();
        Review review2 = Review.builder().build();
        List<Review> reviews = Lists.newArrayList(review1, review2);

        when(reviewService.findAllByRestaurantId(anyInt())).thenReturn(reviews);
        when(restaurantService.getRestaurant(anyInt())).thenReturn(returned);

        mockMvc.perform(get("/restaurant/reviews?restId={id}", ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("newReview", Matchers.notNullValue(Review.class)))
                .andExpect(model().attribute("reviews", Matchers.hasSize(2)))
                .andExpect(model().attribute("restaurant", Matchers.equalTo(returned)))
                .andExpect(view().name("reviews"));
    }
}