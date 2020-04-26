package ru.icoltd.rvs.controller;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.service.RestaurantService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

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
        returned = withId(getMockRestaurant());
    }

    @Test
    void testListRestaurants() throws Exception {
        List<Restaurant> returnedList = getMockRestaurants(2);

        when(restaurantService.findAll()).thenReturn(returnedList);

        mockMvc.perform(get("/restaurant/showAll"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurants", Matchers.hasSize(2)))
                .andExpect(view().name("restaurant-list"));
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
        RestaurantDetail detail = getMockRestaurantDetail();
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("id", Lists.newArrayList(String.valueOf(returned.getId())));
        valueMap.put("name", Lists.newArrayList(returned.getName()));
        valueMap.put("city", Lists.newArrayList(detail.getCity()));
        valueMap.put("street", Lists.newArrayList(detail.getStreet()));
        valueMap.put("phone", Lists.newArrayList(detail.getPhone()));
        valueMap.put("country", Lists.newArrayList(detail.getCountry()));
        valueMap.put("site", Lists.newArrayList(detail.getSite()));

        mockMvc.perform(post("/restaurant/save")
                .params(valueMap)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/restaurant/showAll"))
                .andExpect(status().isFound());
        verify(restaurantService).save(any(Restaurant.class));
    }

    @Test
    void testDelete() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(returned);
        mockMvc.perform(get("/restaurant/{id}/delete", returned.getId()))
                .andExpect(redirectedUrl("/restaurant/showAll"))
                .andExpect(status().isFound());
        verify(restaurantService).remove(eq(returned));
    }

    @Test
    void testUpdate() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(returned);
        mockMvc.perform(get("/restaurant/{id}/update", returned.getId()))
                .andExpect(model().attribute("detail", Matchers.notNullValue(RestaurantDetail.class)))
                .andExpect(model().attribute("restaurant", Matchers.notNullValue(Restaurant.class)))
                .andExpect(view().name("restaurant-form"));
    }
}