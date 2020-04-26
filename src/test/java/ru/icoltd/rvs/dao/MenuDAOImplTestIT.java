package ru.icoltd.rvs.dao;

import com.google.common.collect.Lists;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class MenuDAOImplTestIT {

    private static final Long ID = 1L;

    @Autowired
    private MenuDAO dao;

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Test
    @Transactional
    void testFindById() {
        Menu result = dao.findById(ID).get();
        assertNotNull(result);
        assertEquals(ID, result.getId());
    }

    @Test
    @Transactional
    void testFindByIdNotFound() {
        assertFalse(dao.findById(-1L).isPresent());
    }

    @Test
    @Transactional
    @Rollback
    void testSaveMenu() {
        Menu savedMenu = MockDataUtils.getMockMenu();
        savedMenu.setRestaurant(restaurantDAO.findById(ID).get());
        dao.makePersistent(savedMenu);
        List<Menu> allByRestaurantId = Lists.newArrayList(dao.findAllByRestaurantId(ID));
        assertEquals(3, allByRestaurantId.size());
        assertThat(allByRestaurantId, Matchers.hasItem(savedMenu));
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteMenu() {
        dao.removeById(ID);
        assertFalse(dao.findById(ID).isPresent());
    }

    @Test
    @Transactional
    void testFindAllByRestaurantId() {
        List<Menu> result = Lists.newArrayList(dao.findAllByRestaurantId(ID));
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    void testGetVotesAmount() throws Exception {
        assertEquals(3L, dao.findById(3L).get().getVotesAmount());
    }
}