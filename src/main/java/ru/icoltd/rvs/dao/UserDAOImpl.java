package ru.icoltd.rvs.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
@Slf4j
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findUserByUserName(String username) {
        TypedQuery<User> query = em
                .createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username =: username", User.class);
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
    public User findUserByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email =: email", User.class)
                .setParameter("email", email);
        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException exc) {
            log.warn("Entity 'User' is not found with email {}", email);
        }
        return result;
    }
}
