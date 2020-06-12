package ru.icoltd.rvs.dao;

import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Review;

@Repository
public class ReviewDAOImpl extends GenericDAOImpl<Review, Long> implements ReviewDAO {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    @Override
    public Iterable<Review> findAllByRestaurantId(Long restaurantId) {
        return em.createQuery("SELECT rev FROM Review rev WHERE rev.restaurant.id = :restaurantId", Review.class)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
