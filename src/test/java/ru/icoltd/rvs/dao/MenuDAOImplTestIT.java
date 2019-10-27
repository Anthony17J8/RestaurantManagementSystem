package ru.icoltd.rvs.dao;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.util.MockDataUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class MenuDAOImplTestIT {

    private static final Integer ID = 1;

    @Autowired
    private MenuDAO dao;

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Test
    @Transactional
    void testFindById() {
        Menu result = dao.findById(ID);
        assertNotNull(result);
        assertEquals(ID, result.getId());
    }

    @Test
    @Transactional
    void testFindByIdNotFound() {
        Menu result = dao.findById(Integer.MAX_VALUE);
        assertNull(result);
    }

    @Test
    @Transactional
    @Rollback
    void testSaveMenu() {
        Menu savedMenu = MockDataUtils.getMockMenu();
        savedMenu.setRestaurant(restaurantDAO.findById(ID));
        dao.saveMenu(savedMenu);
        List<Menu> allByRestaurantId = dao.findAllByRestaurantId(ID);
        assertEquals(3, allByRestaurantId.size());
        assertThat(allByRestaurantId, Matchers.hasItem(savedMenu));
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteMenu() {
        Menu deleted = dao.findById(ID);
        dao.deleteMenu(deleted);
        assertNull(dao.findById(ID));
    }

    @Test
    @Transactional
    void testGetBetweenDates() {
        ZonedDateTime from = ZonedDateTime.of(
                LocalDate.of(2019, Month.FEBRUARY, 9),
                LocalTime.now(),
                ZoneId.systemDefault());

        ZonedDateTime to = ZonedDateTime.of(
                LocalDate.of(2019, Month.FEBRUARY, 14),
                LocalTime.now(),
                ZoneId.systemDefault());

        List<Menu> result = dao.getBetweenDates(from, to);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    void testFindAllByRestaurantId() {
        List<Menu> result = dao.findAllByRestaurantId(ID);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}