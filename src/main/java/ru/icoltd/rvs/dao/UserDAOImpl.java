package ru.icoltd.rvs.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.User;

@Repository
@Slf4j
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findUserByUserName(String username) {
        TypedQuery<User> query = em
                .createQuery("SELECT u FROM User u join fetch u.roles where u.username =: username", User.class);
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
//        Session currentSession = sessionFactory.getCurrentSession();
//        currentSession.saveOrUpdate(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
//        Session currentSession = sessionFactory.getCurrentSession();
//        Query<User> query = currentSession.createQuery("FROM User u where u.email =: email", User.class);
//        query.setParameter("email", email);
//        User result = null;
//        try {
//            result = query.getSingleResult();
//        } catch (NoResultException exc) {
//            log.warn("Entity 'User' is not found with email {}", email);
//        }
//        return result;
    }
}
