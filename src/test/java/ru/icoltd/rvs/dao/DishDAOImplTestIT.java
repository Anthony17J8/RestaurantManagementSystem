package ru.icoltd.rvs.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.util.MockDataUtils;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class DishDAOImplTestIT {

    private static final Integer ID = 1;

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private MenuDAO menuDAO;

    @Test
    @Transactional
    @Rollback
    void testSaveDish() {
        Dish saved = MockDataUtils.getMockDish();
//        saved.setMenu(menuDAO.findById(ID));
//        dishDAO.saveDish(saved);
//        List<Dish> dishListForMenu = dishDAO.getDishListByMenuId(ID);
//        assertThat(dishListForMenu, Matchers.hasSize(4));
    }

    @Test
    @Transactional
    void testGetDish() {
//        Dish result = dishDAO.getDish(ID);
//        assertNotNull(result);
//        assertEquals(ID, result.getId());
    }

    @Test
    @Transactional
    void testGetDishNotFound() {
//        Dish result = dishDAO.getDish(Integer.MAX_VALUE);
//        assertNull(result);
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteDish() {
//        Dish deleted = dishDAO.getDish(ID);
//        dishDAO.deleteDish(deleted);
//        assertEquals(dishDAO.getDishListByMenuId(ID).size(), 2);
    }

    @Test
    @Transactional
    void testGetDishListByMenuId() {
//        List<Dish> result = dishDAO.getDishListByMenuId(ID);
//        assertNotNull(result);
//        assertEquals(3, result.size());
    }
}