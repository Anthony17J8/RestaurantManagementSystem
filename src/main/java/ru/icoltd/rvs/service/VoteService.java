package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.VoteDto;

public interface VoteService {

    VoteDto createNewVote(VoteDto vote);

    VoteDto getLatestVoteByUserId(Long userId);

}
