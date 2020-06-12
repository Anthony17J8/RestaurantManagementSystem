package ru.icoltd.rvs.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.entity.User;

public interface UserService extends UserDetailsService {

    UserDto saveUser(UserDto user, PasswordEncoder passwordEncoder);

    User findUserByEmail(String email);
}
