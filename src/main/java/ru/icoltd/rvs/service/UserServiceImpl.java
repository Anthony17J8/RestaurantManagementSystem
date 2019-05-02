package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.UserDAO;
import ru.icoltd.rvs.entity.Role;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.exception.ObjNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO dao;

    @Autowired
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findUserByUserName(username);
        if (user == null) {
            throw new ObjNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), collectToSimpleGrantedAuthority(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> collectToSimpleGrantedAuthority(Collection<Role> roles) {
        return roles.stream().map((r) -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
