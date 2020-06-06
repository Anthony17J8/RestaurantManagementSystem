package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.dtos.VoteDto;
import ru.icoltd.rvs.mappers.VoteMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteServiceImpl implements VoteService {

    private final VoteDAO dao;

    private final VoteMapper mapper;

    @Override
    public VoteDto getLatestVoteByUserId(Long userId) {
        return dao.getLatestVoteByUserId(userId).map(mapper::voteToVoteDto).orElse(null);
    }

    @Override
    @Transactional
    public VoteDto createNewVote(VoteDto vote) {
        return mapper.voteToVoteDto(dao.makePersistent(mapper.voteDtoToVote(vote)));
    }
}