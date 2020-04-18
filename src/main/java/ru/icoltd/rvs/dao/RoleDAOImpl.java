package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    @Override
    public Set<Role> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        return new HashSet<>(currentSession.createQuery("FROM Role r", Role.class).getResultList());
    }

    @Override
    public Set<Role> findByName(Set<String> roleNames) {
        Session currentSession = sessionFactory.getCurrentSession();
        return new HashSet<>(currentSession.createQuery("FROM Role r WHERE r.name IN (:names)", Role.class)
                .setParameterList("names", roleNames)
                .getResultList());
    }
}
