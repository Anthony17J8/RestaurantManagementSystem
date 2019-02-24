package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Restaurant;

import java.util.List;

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Restaurant> getRestaurants() {

        Session currentSession = sessionFactory.getCurrentSession();

        return currentSession.createQuery("from Restaurant r left join fetch r.restaurantDetail", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant findById(int restaurantId) {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Restaurant> query = currentSession.createQuery("from Restaurant r left join fetch r.menus where r.id=:restaurantId", Restaurant.class);
        query.setParameter("restaurantId", restaurantId);

        return query.getSingleResult();
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
