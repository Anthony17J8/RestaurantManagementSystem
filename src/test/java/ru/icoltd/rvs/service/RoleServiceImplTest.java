package ru.icoltd.rvs.service;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.RoleDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl service;

    @Mock
    private RoleDAO dao;

    @Test
    void findAll() {
        service.findAll();

        verify(dao).findAll();
    }

    @Test
    void findByName() {
        service.findByName(Sets.newHashSet());

        verify(dao).findByName(anySet());
    }
}