package ru.icoltd.rvs.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Menu;

import javax.persistence.NoResultException;


@Repository
@Slf4j
public class MenuDAOImpl implements MenuDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Menu findById(int menuId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Menu> query = currentSession.createQuery(
                "from Menu m left join fetch m.dishes" +
                        " join fetch m.restaurant where m.id=:menuId", Menu.class);
        query.setParameter("menuId", menuId);

        Menu result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException exc) {
            log.warn("Entity 'Menu' is not found with id {}", menuId);
        }

        return result;
    }

    @Override
    public void saveMenu(Menu menu) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(menu);
    }

    @Override
    public void deleteMenu(Menu menu) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(menu);
    }
}
