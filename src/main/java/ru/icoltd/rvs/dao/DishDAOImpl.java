package ru.icoltd.rvs.dao;

import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Dish;

@Repository
public class DishDAOImpl extends GenericDAOImpl<Dish, Long> implements DishDAO {

    public DishDAOImpl() {
        super(Dish.class);
    }

    @Override
    public Iterable<Dish> findAllInMenu(Long menuId) {
        return em.createQuery("SELECT d FROM Dish d WHERE d.menu.id = :menuId", Dish.class)
                .setParameter("menuId", menuId).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM Dish d WHERE d.id= :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
