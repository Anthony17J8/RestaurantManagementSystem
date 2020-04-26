package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.User;

public interface UserDAO extends GenericDAO<User, Long> {

    User findUserByUserName(String username);

    User findUserByEmail(String email);
}
