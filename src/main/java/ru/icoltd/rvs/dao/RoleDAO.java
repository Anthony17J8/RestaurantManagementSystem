package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Role;

import java.util.Set;

public interface RoleDAO extends GenericDAO<Role, Long> {

    Iterable<Role> findByName(Set<String> roleNames);
}
