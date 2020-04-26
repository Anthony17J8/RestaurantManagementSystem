package ru.icoltd.rvs.dao;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.util.MockDataUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.icoltd.rvs.util.MockDataUtils.ID;


@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class RestaurantDAOImplTestIT {

    @Autowired
    private RestaurantDAO dao;

    @Test
    @Transactional
    void getRestaurants() {
        List<Restaurant> restaurants = Lists.newArrayList(dao.findAll());
        assertEquals(3, restaurants.size());
    }

    @Test
    @Transactional
    void findById() {
        Restaurant result = dao.findById(ID).get();
        assertNotNull(result);
        assertEquals(ID, result.getId());
    }

    @Test
    @Transactional
    void findByIdNotFound() throws Exception {
        assertFalse(dao.findById(-1L).isPresent());
    }

    @Test
    @Transactional
    @Rollback
    void saveRestaurant() {
        dao.makePersistent(MockDataUtils.getMockRestaurant());
        assertEquals(4, Lists.newArrayList(dao.findAll()).size());
    }

    @Test
    @Transactional
    @Rollback
    void deleteRestaurant() {
        Restaurant deleted = dao.findById(ID).get();
        dao.remove(deleted);
        assertEquals(2, Lists.newArrayList(dao.findAll()).size());
        assertFalse(dao.findById(ID).isPresent());
    }
}