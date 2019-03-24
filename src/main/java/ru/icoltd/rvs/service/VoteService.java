package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Vote;

public interface VoteService {

    void saveVote(Vote vote);

    Vote getLatestVoteByUserId(int userId);

}
