package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.entity.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(dao.findUserByUserName(username))
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username: " + username)
                );
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        dao.makePersistent(user);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }
}
