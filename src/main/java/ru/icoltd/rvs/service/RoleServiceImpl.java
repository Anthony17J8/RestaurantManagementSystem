package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.RoleDAO;
import ru.icoltd.rvs.entity.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO dao;

    @Autowired
    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Set<Role> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public Set<Role> findByName(Set<String> roleNames) {
        return dao.findByName(roleNames);
    }
}
