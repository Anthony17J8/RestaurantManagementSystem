package ru.icoltd.rvs.dao;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.util.MockDataUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class UserDAOImplTestIT {

    @Autowired
    private UserDAO dao;

    @Autowired
    private RoleDAO roleDAO;

    @Test
    @Transactional
    void testFindUserByUserName() {
        User result = dao.findUserByUserName("ThomasBl");
        assertNotNull(result);
        assertNotNull(result.getRoles());
    }

    @Test
    @Transactional
    void testFindUserByUserNameNotFound() {
        assertNull(dao.findUserByUserName("Unknown user"));
    }

    @Test
    @Transactional
    @Rollback
    void testSaveUser() {
        User saved = MockDataUtils.getMockUser();
        saved.setRoles(Sets.newHashSet(roleDAO.findAll()));
        assertEquals(dao.makePersistent(saved), dao.findUserByUserName(saved.getUsername()));
    }


    @Test
    @Transactional
    void testFindUserByEmail() {
        User result = dao.findUserByEmail("bl21@gmail.com");
        assertNotNull(result);
        assertEquals("Thomas", result.getFirstName());
        assertNotNull(result.getRoles());
    }

    @Test
    @Transactional
    void testFindUserByEmailNotFound() {
        assertNull(dao.findUserByEmail("Invalid email"));
    }
}