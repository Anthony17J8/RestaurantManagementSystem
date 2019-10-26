package ru.icoltd.rvs.service;

import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.User;

import java.time.LocalDateTime;

public interface VoteService {

    void saveOrUpdateVote(Menu menu, LocalDateTime now, User currentUser);

}
