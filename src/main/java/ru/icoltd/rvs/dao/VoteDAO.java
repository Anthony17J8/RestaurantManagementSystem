package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Vote;

import java.util.List;

public interface VoteDAO {

    void saveVote(Vote vote);

    Vote getLatestVoteByUserId(int userId);

    List<Vote> findAllByUserId(int userId);
}
