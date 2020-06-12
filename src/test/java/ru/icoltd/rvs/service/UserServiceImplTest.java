package ru.icoltd.rvs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.icoltd.rvs.dao.RoleDAO;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.mappers.UserMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    private User mockUser;

    private UserDto mockUserDto;

    @BeforeEach
    void setUp() {
        mockUser = withId(getMockUser());
        mockUserDto = getMockUserDto();
    }

    @Test
    void testLoadUserByUsername() {
        when(userDAO.findUserByUserName(anyString())).thenReturn(mockUser);
        service.loadUserByUsername(mockUser.getEmail());
        verify(userDAO).findUserByUserName(anyString());
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(mockUser.getEmail()));
    }

    @Test
    void testSaveUser() {
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("password");
        when(mapper.toUser(any(UserDto.class))).thenReturn(mockUser);

        service.saveUser(mockUserDto, passwordEncoder);

        verify(userDAO).makePersistent(eq(mockUser));
        verify(passwordEncoder).encode(any(CharSequence.class));
        verify(mapper).toUser(any(UserDto.class));
    }

    @Test
    void testFindUserByEmail() {
        service.findUserByEmail(mockUser.getEmail());
        verify(userDAO).findUserByEmail(anyString());

    }
}