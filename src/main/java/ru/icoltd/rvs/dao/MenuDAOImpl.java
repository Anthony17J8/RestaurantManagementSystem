package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Menu;


@Repository
public class MenuDAOImpl implements MenuDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Menu findById(int menuId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Menu> query = currentSession.createQuery(
                "from Menu m left join fetch m.dishes left join fetch m.votes" +
                        " join fetch m.restaurant where m.id=:menuId", Menu.class);
        query.setParameter("menuId", menuId);

        return query.getSingleResult();
    }

    @Override
    public void saveMenu(Menu menu) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(menu);
    }
}
