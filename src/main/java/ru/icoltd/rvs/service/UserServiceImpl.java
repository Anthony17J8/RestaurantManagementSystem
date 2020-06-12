package ru.icoltd.rvs.service;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RoleDAO;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.mappers.UserMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final UserMapper mapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userDAO.findUserByUserName(username))
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with username: " + username)
                );
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto user, PasswordEncoder passwordEncoder) {
        user = Optional.of(user).map(
                (u) -> UserDto.builder()
                        .username(u.getUsername())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .email(u.getEmail())
                        .dateOfBirth(u.getDateOfBirth())
                        .roles(u.getRoles())
                        .password(passwordEncoder.encode(u.getPassword()))).get().build();
        User storedUser = mapper.toUser(user);
        storedUser.setRoles(Sets.newHashSet(roleDAO.findByName(user.getRoles())));
        return mapper.fromUser(userDAO.makePersistent(storedUser));
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }
}
