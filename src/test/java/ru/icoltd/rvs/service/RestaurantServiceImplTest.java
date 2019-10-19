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
import ru.icoltd.rvs.util.MockDataUtils;

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

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    private RestaurantServiceImpl service;

    private Restaurant restaurantMock;

    @BeforeEach
    void setUp() {
        restaurantMock = MockDataUtils.getMockRestaurant();
    }

    @Test
    void testGetRestaurants() {
        List<Restaurant> returnedList = MockDataUtils.getMockRestaurants(2);

        when(restaurantDAO.getRestaurants()).thenReturn(returnedList);

        List<Restaurant> resultList = service.getRestaurants();

        assertEquals(resultList.size(), returnedList.size());

        verify(restaurantDAO).getRestaurants();
    }

    @Test
    void testGetRestaurant() {
        when(restaurantDAO.findById(anyInt())).thenReturn(restaurantMock);

        Restaurant result = service.getRestaurant(restaurantMock.getId());

        assertNotNull(result);
        assertEquals(result.getId(), restaurantMock.getId());

        verify(restaurantDAO).findById(anyInt());
    }

    @Test
    void testGetRestaurantNotFound() {
        assertThrows(ObjNotFoundException.class, () -> service.getRestaurant(restaurantMock.getId()));
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