package ru.icoltd.rvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.mappers.MenuMapper;
import ru.icoltd.rvs.util.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteDAO dao;

    private final MenuMapper menuMapper;

    @Override
    @Transactional
    public void saveOrUpdateVote(MenuDto menuDto, LocalDateTime now, User currentUser) {
        Optional<Vote> latestVote = dao.getLatestVoteByUserId(currentUser.getId());
        Menu menu = menuMapper.menuDtoToMenu(menuDto);

        if (latestVote.isPresent() && DateTimeUtils.isBetweenRange(latestVote.get().getDateTime(), now)) {
            Vote latest = latestVote.get();
            latest.setDateTime(now);
            latest.setMenu(menu);
            dao.makePersistent(latest);
        } else {
            dao.makePersistent(Vote.builder().menu(menu).user(currentUser).dateTime(now).build());
        }
    }
}
