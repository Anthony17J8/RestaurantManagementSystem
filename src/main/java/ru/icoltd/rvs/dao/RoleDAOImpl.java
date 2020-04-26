package ru.icoltd.rvs.dao;

import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Role;

import java.util.Set;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {

    public RoleDAOImpl() {
        super(Role.class);
    }

    @Override
    public Iterable<Role> findByName(Set<String> roleNames) {
        return em.createQuery("SELECT r FROM Role r WHERE r.name IN (:names)", Role.class)
                .setParameter("names", roleNames)
                .getResultList();
    }
}
