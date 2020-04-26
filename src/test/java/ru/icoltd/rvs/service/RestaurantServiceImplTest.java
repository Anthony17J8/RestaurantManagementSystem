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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    private RestaurantServiceImpl service;

    private Restaurant restaurantMock;

    @BeforeEach
    void setUp() {
        restaurantMock = withId(getMockRestaurant());
    }

    @Test
    void testGetRestaurants() {
        List<Restaurant> returnedList = getMockRestaurants(2);
        when(restaurantDAO.findAll()).thenReturn(returnedList);
        List<Restaurant> resultList = service.findAll();
        assertEquals(resultList.size(), returnedList.size());
        verify(restaurantDAO).findAll();
    }

    @Test
    void testGetRestaurant() {
        when(restaurantDAO.findById(anyLong())).thenReturn(Optional.of(restaurantMock));
        Restaurant result = service.findById(restaurantMock.getId());
        assertNotNull(result);
        assertEquals(result.getId(), restaurantMock.getId());
        verify(restaurantDAO).findById(anyLong());
    }

    @Test
    void testGetRestaurantNotFound() {
        assertThrows(ObjNotFoundException.class, () -> service.findById(restaurantMock.getId()));
    }

    @Test
    void testSaveRestaurant() {
        service.save(restaurantMock);
        verify(restaurantDAO).makePersistent(eq(restaurantMock));
    }

    @Test
    void testDeleteRestaurant() {
        service.remove(restaurantMock);
        verify(restaurantDAO).remove(eq(restaurantMock));
    }
}