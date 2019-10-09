package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Dish;

import java.util.List;

@Repository
public class DishDAOImpl implements DishDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public DishDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveDish(Dish dish) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(dish);
    }

    @Override
    public Dish getDish(int dishId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Dish.class, dishId);
    }

    @Override
    public void deleteDish(Dish dish) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(dish);
    }

    @Override
    public List<Dish> getDishListByMenuId(int menuId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Dish> query = currentSession.createQuery("from Dish d where d.menu.id=:menuId", Dish.class);
        query.setParameter("menuId", menuId);
        return query.getResultList();
    }
}
