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
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.util.MockDataUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.icoltd.rvs.util.MockDataUtils.ID;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class DishDAOImplTestIT {

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private MenuDAO menuDAO;

    @Test
    @Transactional
    @Rollback
    void testSaveDish() {
        Dish saved = MockDataUtils.getMockDish();
        saved.setMenu(menuDAO.findById(ID).get());
        dishDAO.makePersistent(saved);
        assertThat(Lists.newArrayList(dishDAO.findAllInMenu(ID)), Matchers.hasSize(4));
    }

    @Test
    @Transactional
    void testGetDish() {
        Dish result = dishDAO.findById(ID).get();
        assertNotNull(result);
        assertEquals(ID, result.getId());
    }

    @Test
    @Transactional
    void testGetDishNotFound() {
        assertFalse(dishDAO.findById(-1L).isPresent());
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteDish() {
        Dish deleted = dishDAO.findById(ID).get();
        dishDAO.remove(deleted);
        assertEquals(Lists.newArrayList(dishDAO.findAllInMenu(ID)).size(), 2);
    }

    @Test
    @Transactional
    void testGetDishListByMenuId() {
        List<Dish> result = Lists.newArrayList(dishDAO.findAllInMenu(ID));
        assertNotNull(result);
        assertEquals(3, result.size());
    }
}