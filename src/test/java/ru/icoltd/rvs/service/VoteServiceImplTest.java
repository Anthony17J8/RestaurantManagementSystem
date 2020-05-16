package ru.icoltd.rvs.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.mappers.MenuMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.icoltd.rvs.util.MockDataUtils.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl service;

    @Mock
    private VoteDAO dao;

    @Mock
    private MenuMapper menuMapper;

    @Captor
    private ArgumentCaptor<Vote> voteArgumentCaptor;

    @Test
    void testSaveOrUpdateVoteWhenNew() {
        when(menuMapper.menuDtoToMenu(any(MenuDto.class))).thenReturn(getMockMenu());
        service.saveOrUpdateVote(getMockMenuDto(), LocalDateTime.now(), withId(getMockUser()));
        verify(dao).makePersistent(voteArgumentCaptor.capture());
        assertNull(voteArgumentCaptor.getValue().getId());
    }

    @Test
    void testSaveOrUpdateVoteWhenExist() {
        Vote latest = withId(getMockVote());
        latest.setDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        when(menuMapper.menuDtoToMenu(any(MenuDto.class))).thenReturn(getMockMenu());
        when(dao.getLatestVoteByUserId(anyLong())).thenReturn(Optional.of(latest));
        service.saveOrUpdateVote(getMockMenuDto(), LocalDateTime.now(), withId(getMockUser()));
        verify(dao).makePersistent(voteArgumentCaptor.capture());
        assertNotNull(voteArgumentCaptor.getValue().getId());
    }
}