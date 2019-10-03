package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.User;

public interface UserDAO {

    User findUserByUserName(String username);

    void saveUser(User user);

    User findUserByEmail(String email);
}
