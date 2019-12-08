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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        LocalDateTime from = LocalDateTime.of(
                LocalDate.of(2019, Month.FEBRUARY, 9),
                LocalTime.now());

        LocalDateTime to = LocalDateTime.of(
                LocalDate.of(2019, Month.FEBRUARY, 14),
                LocalTime.now());

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

    @Test
    @Transactional
    void testGetVotesAmount() throws Exception {
        assertEquals(3L, dao.findById(3).getVotesAmount());
    }

    @Test
    @Transactional
    void testGetTotalAmount() throws Exception {
        assertEquals(
                new BigDecimal(270).doubleValue(),
                dao.findById(ID).getTotalAmount().doubleValue()
        );
    }
}