package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.User;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findUserByUserName(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("FROM User u join fetch u.roles where u.username =: username", User.class);
        query.setParameter("username", username);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException exc) {
            log.warn("Entity 'User' is not found with username {}", username);
        }
        return result;
    }

    @Override
    public void saveUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    @Override
    public User findUserByEmail(String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("FROM User u where u.email =: email", User.class);
        query.setParameter("email", email);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException exc) {
            log.warn("Entity 'User' is not found with email {}", email);
        }
        return result;
    }
}
