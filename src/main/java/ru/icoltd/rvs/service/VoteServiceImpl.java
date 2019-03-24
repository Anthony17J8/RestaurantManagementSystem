package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Vote;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteDAO dao;

    @Autowired
    public void setDao(VoteDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void saveVote(Vote vote) {
        dao.saveVote(vote);
    }

    @Override
    @Transactional
    public Vote getLatestVoteByUserId(int userId) {
        return dao.getVoteLatestVoteByUserId(userId);
    }
}
