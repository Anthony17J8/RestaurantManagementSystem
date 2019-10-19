package ru.icoltd.rvs.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.Restaurant;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringJUnitWebConfig(classes = {ApplicationConfig.class, TestConfig.class})
class RestaurantDAOImplTestIT {

    private static final Integer ID = 1;

    @Autowired
    private RestaurantDAO dao;

    @Test
    @Transactional
    void getRestaurants() {
        List<Restaurant> restaurants = dao.getRestaurants();
        assertEquals(3, restaurants.size());
    }

    @Test
    @Transactional
    void findById() {
        Restaurant result = dao.findById(ID);
        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals("Osteria Francescana", result.getName());
    }

    @Test
    @Transactional
    void findByIdNotFound() throws Exception {
        Restaurant result = dao.findById(Integer.MIN_VALUE);
        assertNull(result);
    }

    @Test
    @Transactional
    @Rollback
    void saveRestaurant() {
        dao.saveRestaurant(Restaurant.builder().name("New Restaurant").build());
        assertEquals(4, dao.getRestaurants().size());
    }

    @Test
    @Transactional
    @Rollback
    void deleteRestaurant() {
        Restaurant deleted = dao.findById(ID);
        dao.deleteRestaurant(deleted);
        assertEquals(2, dao.getRestaurants().size());
        assertNull(dao.findById(ID));
    }
}