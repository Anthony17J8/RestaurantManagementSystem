package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Vote;

import java.util.Optional;

public interface VoteDAO extends GenericDAO<Vote, Long> {

    Optional<Vote> getLatestVoteByUserId(Long userId);

    Iterable<Vote> findAllByUserId(Long userId);
}
