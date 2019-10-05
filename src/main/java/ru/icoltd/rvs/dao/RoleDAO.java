package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Role;

import java.util.Set;

public interface RoleDAO {

    Set<Role> findAll();

    Set<Role> findByName(Set<String> roleNames);
}
