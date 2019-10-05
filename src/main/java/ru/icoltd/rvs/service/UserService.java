package ru.icoltd.rvs.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.icoltd.rvs.entity.User;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    User findUserByEmail(String email);
}
