package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Dish;

@Repository
public class DishDAOImpl implements DishDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
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
}
