package ru.icoltd.rvs.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Menu;


@Repository
@Slf4j
public class MenuDAOImpl extends GenericDAOImpl<Menu, Long> implements MenuDAO {

    public MenuDAOImpl() {
        super(Menu.class);
    }

    @Override
    public Iterable<Menu> findAllByRestaurantId(Long restaurantId) {
        return em.createQuery(
                "SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId", Menu.class)
                .setParameter("restaurantId", restaurantId).getResultList();
    }

    @Override
    public void removeById(Long id) {
        em.createQuery("DELETE FROM Menu m WHERE m.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
