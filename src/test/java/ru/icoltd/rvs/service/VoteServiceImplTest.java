package ru.icoltd.rvs.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.entity.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Captor
    private ArgumentCaptor<Vote> voteArgumentCaptor;

    @Test
    void testSaveOrUpdateVoteWhenNew() {
        service.saveOrUpdateVote(withId(getMockMenu()), LocalDateTime.now(), withId(getMockUser()));
        verify(dao).makePersistent(voteArgumentCaptor.capture());
        assertNull(voteArgumentCaptor.getValue().getId());
    }

    @Test
    void testSaveOrUpdateVoteWhenExist() {
        Vote latest = withId(getMockVote());
        latest.setDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)));
        when(dao.getLatestVoteByUserId(anyLong())).thenReturn(Optional.of(latest));
        service.saveOrUpdateVote(withId(getMockMenu()), LocalDateTime.now(), withId(getMockUser()));
        verify(dao).makePersistent(voteArgumentCaptor.capture());
        assertNotNull(voteArgumentCaptor.getValue().getId());
    }
}