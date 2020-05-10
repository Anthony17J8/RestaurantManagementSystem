package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.RestaurantDAO;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.exception.ObjNotFoundException;
import ru.icoltd.rvs.mappers.RestaurantMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private RestaurantMapper mapper;

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
        List<RestaurantDto> resultList = service.findAll();
        assertEquals(resultList.size(), returnedList.size());
        verify(restaurantDAO).findAll();
    }

    @Test
    void testGetRestaurant() {
        when(restaurantDAO.findById(anyLong())).thenReturn(Optional.of(restaurantMock));
        when(mapper.restaurantToRestaurantDto(eq(restaurantMock))).thenReturn(getMockRestaurantDto());
        RestaurantDto result = service.findById(restaurantMock.getId());
        assertNotNull(result);
        verify(mapper).restaurantToRestaurantDto(any(Restaurant.class));
        verify(restaurantDAO).findById(anyLong());
    }

    @Test
    void testGetRestaurantNotFound() {
        assertThrows(ObjNotFoundException.class, () -> service.findById(restaurantMock.getId()));
    }

    @Test
    void testSaveRestaurant() {
        when(mapper.restaurantDtoToRestaurant(any(RestaurantDto.class))).thenReturn(new Restaurant());
        service.save(new RestaurantDto());
        verify(mapper).restaurantDtoToRestaurant(any(RestaurantDto.class));
        verify(restaurantDAO).makePersistent(any(Restaurant.class));
    }

    @Test
    void testDeleteRestaurant() {
        when(mapper.restaurantDtoToRestaurant(any(RestaurantDto.class))).thenReturn(new Restaurant());
        service.remove(new RestaurantDto());
        verify(mapper).restaurantDtoToRestaurant(any(RestaurantDto.class));
        verify(restaurantDAO).remove(any(Restaurant.class));
    }
}