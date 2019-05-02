package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.User;

public interface UserDAO {

    User findUserById(int id);

    User findUserByUserName(String username);
}
