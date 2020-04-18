package ru.icoltd.rvs.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Restaurant;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class RestaurantDAOImpl extends GenericDAOImpl<Restaurant, Long> implements RestaurantDAO {

    public RestaurantDAOImpl() {
        super(Restaurant.class);
    }

    @Override
    public Optional<Restaurant> findByIdWithReviews(Long restaurantId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = cb.createQuery(Restaurant.class);
        Root<Restaurant> r = query.from(Restaurant.class);
        r.fetch("reviews", JoinType.LEFT);
        query.select(r).where(cb.equal(r.get("id"), restaurantId));
        return Optional.of(em.createQuery(query).getSingleResult());
    }

    @Override
    public Optional<Restaurant> findByIdWithMenus(Long restaurantId) {
        EntityGraph<?> eg = em.getEntityGraph("RestaurantMenus");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", eg);
        return Optional.ofNullable(em.find(Restaurant.class, restaurantId, properties));
    }
}
