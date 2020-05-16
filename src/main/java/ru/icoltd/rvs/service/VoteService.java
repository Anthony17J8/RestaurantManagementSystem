package ru.icoltd.rvs.service;

import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.User;

import java.time.LocalDateTime;

public interface VoteService {

    void saveOrUpdateVote(MenuDto menu, LocalDateTime now, User currentUser);

}
