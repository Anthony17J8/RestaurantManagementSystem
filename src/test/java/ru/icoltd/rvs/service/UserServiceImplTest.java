package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.entity.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.getMockUser;
import static ru.icoltd.rvs.util.MockDataUtils.withId;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserDAO dao;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = withId(getMockUser());
    }

    @Test
    void testLoadUserByUsername() {
        when(dao.findUserByUserName(anyString())).thenReturn(mockUser);
        service.loadUserByUsername(mockUser.getEmail());
        verify(dao).findUserByUserName(anyString());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(mockUser.getEmail()));
    }

    @Test
    void testSaveUser() {
        service.saveUser(mockUser);
        verify(dao).saveUser(eq(mockUser));
    }

    @Test
    void testFindUserByEmail() {
        service.findUserByEmail(mockUser.getEmail());
        verify(dao).findUserByEmail(anyString());
    }
}