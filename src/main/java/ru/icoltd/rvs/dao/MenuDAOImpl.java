package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Menu;

import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import java.time.ZonedDateTime;
import java.util.List;


@Repository
public class MenuDAOImpl implements MenuDAO {

    private static final Logger log = LoggerFactory.getLogger(MenuDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public MenuDAOImpl(SessionFactory sessionFactory) {
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

    @Override
    public List<Menu> getBetweenDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Menu> query = currentSession.createQuery(
                "from Menu m " +
                        "where m.date between :startDate and :endDate order by m.votes.size desc, m.date desc", Menu.class);
        query.setParameter("endDate", endDate, TemporalType.DATE);
        query.setParameter("startDate", startDate, TemporalType.DATE);
        return query.getResultList();
    }

    @Override
    public List<Menu> findAllByRestaurantId(int restaurantId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Menu> query = currentSession.createQuery("FROM Menu WHERE restaurant.id=:restaurantId", Menu.class);
        query.setParameter("restaurantId", restaurantId);
        return query.getResultList();
    }
}
