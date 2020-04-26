package ru.icoltd.rvs.dao;

import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import ru.icoltd.rvs.config.ApplicationConfig;
import ru.icoltd.rvs.config.TestConfig;
import ru.icoltd.rvs.entity.User;
import ru.icoltd.rvs.entity.Vote;
import ru.icoltd.rvs.util.MockDataUtils;

import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.icoltd.rvs.util.MockDataUtils.ID;

@SpringJUnitWebConfig({ApplicationConfig.class, TestConfig.class})
class VoteDAOImplTestIT {

    @Autowired
    private VoteDAO voteDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MenuDAO menuDAO;

    @Test
    @Transactional
    @Rollback
    void testSaveVote() {
        Vote savedVote = MockDataUtils.getMockVote();
        User user = userDAO.findUserByUserName("ThomasBl");
        savedVote.setMenu(menuDAO.findById(ID).get());
        savedVote.setUser(user);
        voteDAO.makePersistent(savedVote);
        assertThat(Lists.newArrayList(voteDAO.findAllByUserId(user.getId())), Matchers.hasSize(2));
    }

    @Test
    @Transactional
    void testGetLatestVoteByUserId() {
        Vote latest = voteDAO.getLatestVoteByUserId(2L).get();
        StreamSupport.stream(voteDAO.findAllByUserId(2L).spliterator(), false)
                .forEach(v -> assertFalse(v.getDateTime().isAfter(latest.getDateTime()))
                );
    }

    @Test
    @Transactional
    void testFindAllByUserId() {
        assertThat(Lists.newArrayList(voteDAO.findAllByUserId(2L)), Matchers.hasSize(2));
        assertThat(Lists.newArrayList(voteDAO.findAllByUserId(1L)), Matchers.hasSize(1));
    }
}