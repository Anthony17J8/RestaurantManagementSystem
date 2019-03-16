package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Dish;

@Repository
public class DishDAOImpl implements DishDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveDish(Dish dish) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(dish);
    }
}
