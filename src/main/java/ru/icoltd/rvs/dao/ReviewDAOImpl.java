package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Review;

import java.util.List;

@Repository
public class ReviewDAOImpl implements ReviewDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ReviewDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Review> findAllByRestaurantId(int restaurantId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Review> query = currentSession.createQuery("FROM Review WHERE restaurant.id =:restaurantId", Review.class);
        query.setParameter("restaurantId", restaurantId);
        return query.getResultList();
    }

    @Override
    public void saveReview(Review review) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(review);
    }
}