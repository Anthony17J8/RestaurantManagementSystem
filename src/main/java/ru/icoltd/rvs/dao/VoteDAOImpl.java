package ru.icoltd.rvs.dao;

import org.springframework.stereotype.Repository;
import ru.icoltd.rvs.entity.Vote;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class VoteDAOImpl extends GenericDAOImpl<Vote, Long> implements VoteDAO {

    public VoteDAOImpl() {
        super(Vote.class);
    }

    @Override
    public Optional<Vote> getLatestVoteByUserId(Long userId) {
        try {
            return Optional.of(em.createQuery(
                    "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.dateTime DESC", Vote.class)
                    .setMaxResults(1)
                    .setParameter("userId", userId)
                    .getSingleResult());
        } catch (NoResultException exc) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Vote> findAllByUserId(Long userId) {
        return  em.createQuery("SELECT v from Vote v where v.user.id = :userId", Vote.class)
        .setParameter("userId", userId)
        .getResultList();
    }
}
