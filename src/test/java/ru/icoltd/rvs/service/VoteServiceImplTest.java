package ru.icoltd.rvs.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.icoltd.rvs.dao.VoteDAO;
import ru.icoltd.rvs.dtos.VoteDto;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.mappers.VoteMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
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
    private VoteMapper voteMapper;

    @Captor
    private ArgumentCaptor<Vote> voteArgumentCaptor;

    @Test
    void testCreateNewVote() {
        VoteDto voteDto = getMockVoteDto();
        when(voteMapper.voteDtoToVote(any(VoteDto.class))).thenReturn(getMockVote());
        service.createNewVote(voteDto);
        verify(dao).makePersistent(voteArgumentCaptor.capture());
        assertNull(voteArgumentCaptor.getValue().getId());
    }

    @Test
    void testGetLatestVoteByUserId() {
        when(dao.getLatestVoteByUserId(anyLong())).thenReturn(Optional.of(withId(getMockVote())));
        service.getLatestVoteByUserId(ID);
        verify(dao).getLatestVoteByUserId(anyLong());
        verify(voteMapper).voteToVoteDto(any(Vote.class));
    }
}