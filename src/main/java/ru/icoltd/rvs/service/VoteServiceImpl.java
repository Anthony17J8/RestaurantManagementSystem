package ru.icoltd.rvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.util.DateTimeUtils;

import java.time.LocalDateTime;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteDAO dao;

    @Autowired
    public VoteServiceImpl(VoteDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void saveOrUpdateVote(Menu menu, LocalDateTime now, User currentUser) {
        Vote latestVote = dao.getLatestVoteByUserId(currentUser.getId());

        if (latestVote != null && DateTimeUtils.isBetween(latestVote.getDateTime(), now)) {
            latestVote.setMenu(menu);
            latestVote.setDateTime(now);
            dao.saveVote(latestVote);
        } else {
            dao.saveVote(new Vote(currentUser, menu, now));
        }
    }
}
