package ru.icoltd.rvs.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Restaurant;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Slf4j
public class RestaurantDAOImpl implements RestaurantDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public RestaurantDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Restaurant> getRestaurants() {

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.createQuery("from Restaurant r", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(int restaurantId) {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Restaurant> query = currentSession.createQuery(
                "from Restaurant r where r.id=:restaurantId",
                Restaurant.class);
        query.setParameter("restaurantId", restaurantId);

        Restaurant result = null;

        try {
            result = query.getSingleResult();
        } catch (NoResultException exc) {
            log.warn("Entity 'Restaurant' is not found with id {}", restaurantId);
        }

        return result;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(restaurant);
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(restaurant);
    }
}
