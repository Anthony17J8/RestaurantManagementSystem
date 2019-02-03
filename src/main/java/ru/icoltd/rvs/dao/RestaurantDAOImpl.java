package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        return currentSession.createQuery("from Restaurant r JOIN fetch r.restaurantDetail", Restaurant.class).getResultList();
    }
}
