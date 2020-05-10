package ru.icoltd.rvs.controller;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.service.RestaurantService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.icoltd.rvs.util.MockDataUtils.getMockRestaurantDto;
import static ru.icoltd.rvs.util.MockDataUtils.getMockRestaurantDtos;

@ExtendWith(MockitoExtension.class)
class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController controller;

    private MockMvc mockMvc;

    private RestaurantDto returned;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
        returned = getMockRestaurantDto();
    }

    @Test
    void testListRestaurants() throws Exception {
        List<RestaurantDto> returnedList = getMockRestaurantDtos(2);

        when(restaurantService.findAll()).thenReturn(returnedList);

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurants", Matchers.hasSize(2)))
                .andExpect(view().name("restaurants"));
    }

    @Test
    void testShowAddRestaurantForm() throws Exception {
        mockMvc.perform(get("/restaurants/new"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurant", Matchers.notNullValue(RestaurantDto.class)))
                .andExpect(view().name("restaurant-new"));
    }

    @Test
    void testAddRestaurant() throws Exception {
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("id", Lists.newArrayList(String.valueOf(returned.getId())));
        valueMap.put("name", Lists.newArrayList(returned.getName()));
        valueMap.put("city", Lists.newArrayList(returned.getRestaurantDetail().getCity()));
        valueMap.put("street", Lists.newArrayList(returned.getRestaurantDetail().getStreet()));
        valueMap.put("phone", Lists.newArrayList(returned.getRestaurantDetail().getPhoneNumber()));
        valueMap.put("country", Lists.newArrayList(returned.getRestaurantDetail().getCountry()));
        valueMap.put("site", Lists.newArrayList(returned.getRestaurantDetail().getUrl()));

        mockMvc.perform(post("/restaurants")
                .params(valueMap)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(redirectedUrl("/restaurants"))
                .andExpect(status().isFound());
        verify(restaurantService).save(any(RestaurantDto.class));
    }

    @Test
    void testDelete() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(returned);
        mockMvc.perform(get("/restaurants/{id}/delete", returned.getId()))
                .andExpect(redirectedUrl("/restaurants"))
                .andExpect(status().isFound());
        verify(restaurantService).remove(eq(returned));
    }

    @Test
    @Disabled
    void testUpdate() throws Exception {
        when(restaurantService.findById(anyLong())).thenReturn(returned);
        mockMvc.perform(get("/restaurants/{id}/update", returned.getId()))
                .andExpect(model().attribute("restaurant", Matchers.notNullValue(Restaurant.class)))
                .andExpect(view().name("restaurant-new"));
    }
}