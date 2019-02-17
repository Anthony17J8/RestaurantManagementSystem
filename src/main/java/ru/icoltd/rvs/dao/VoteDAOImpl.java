package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.entity.Vote;

@Repository
public class VoteDAOImpl implements VoteDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void saveVote(Vote vote) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(vote);
    }
}
