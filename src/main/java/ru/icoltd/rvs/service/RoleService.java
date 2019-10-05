package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findAll();

    Set<Role> findByName(Set<String> roleNames);
}
