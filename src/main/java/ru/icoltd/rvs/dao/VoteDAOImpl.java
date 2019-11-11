package ru.icoltd.rvs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Vote;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class VoteDAOImpl implements VoteDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public VoteDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveVote(Vote vote) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(vote);
    }

    @Override
    public Vote getLatestVoteByUserId(int userId) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();

            Query<Vote> query = currentSession.createQuery(
                    "from Vote where user.id=:userId order by dateTime desc", Vote.class)
                    .setMaxResults(1);
            query.setParameter("userId", userId);
            return query.getSingleResult();
        } catch (NoResultException exc) {
            return null;
        }
    }

    @Override
    public List<Vote> findAllByUserId(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Vote> query = currentSession.createQuery("from Vote where user.id=:userId", Vote.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
