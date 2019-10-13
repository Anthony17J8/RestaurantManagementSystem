package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    private static final Integer ID = 1;

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    private RestaurantServiceImpl service;

    private Restaurant restaurantMock;

    @BeforeEach
    void setUp() {
        restaurantMock = Restaurant.builder().id(ID).build();
    }

    @Test
    void testGetRestaurants() {
        List<Restaurant> returnedList = new ArrayList<>();
        Restaurant restaurant1 = Restaurant.builder().id(1).build();
        Restaurant restaurant2 = Restaurant.builder().id(2).build();
        returnedList.add(restaurant1);
        returnedList.add(restaurant2);

        when(restaurantDAO.getRestaurants()).thenReturn(returnedList);

        List<Restaurant> resultList = service.getRestaurants();

        assertEquals(resultList.size(), returnedList.size());

        verify(restaurantDAO).getRestaurants();
    }

    @Test
    void testGetRestaurant() {
        when(restaurantDAO.findById(anyInt())).thenReturn(restaurantMock);

        Restaurant result = service.getRestaurant(ID);

        assertNotNull(result);
        assertEquals(result.getId(), ID);

        verify(restaurantDAO).findById(anyInt());
    }

    @Test
    void testGetRestaurantNotFound() {
        assertThrows(ObjNotFoundException.class, () -> service.getRestaurant(ID));
    }

    @Test
    void testSaveRestaurant() {
        service.saveRestaurant(restaurantMock);

        verify(restaurantDAO).saveRestaurant(eq(restaurantMock));
    }

    @Test
    void testDeleteRestaurant() {
        service.deleteRestaurant(restaurantMock);

        verify(restaurantDAO).deleteRestaurant(eq(restaurantMock));
    }
}