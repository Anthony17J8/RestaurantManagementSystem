package ru.icoltd.rvs.dao;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.Role;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class RoleDAOImplTestIT {

    @Autowired
    private RoleDAO dao;

    @Test
    @Transactional
    void testFindAll() {
        Set<Role> roles = dao.findAll();
        assertThat(roles, Matchers.hasSize(2));
    }

    @Test
    @Transactional
    void testFindByName() {
        Set<String> roleNames = dao.findAll().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        assertThat(dao.findByName(roleNames), Matchers.hasSize(2));
    }
}