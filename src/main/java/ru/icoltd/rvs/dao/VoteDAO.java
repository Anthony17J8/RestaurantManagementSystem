package ru.icoltd.rvs.dao;

import ru.icoltd.rvs.entity.Vote;

public interface VoteDAO {

    void saveVote(Vote vote);

    Vote getVoteLatestVoteByUserId(int userId);
}
