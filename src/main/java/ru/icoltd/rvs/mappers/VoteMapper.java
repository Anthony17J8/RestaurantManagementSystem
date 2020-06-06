package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.MenuDto;
import ru.icoltd.rvs.dtos.VoteDto;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Vote;

@Mapper
public interface VoteMapper {

    VoteDto voteToVoteDto(Vote vote);

    Vote voteDtoToVote(VoteDto voteDto);

    @Mapping(source = "restaurant", target = "restaurant", ignore = true)
    MenuDto fromMenu(Menu menu);

    @Mapping(source = "restaurant", target = "restaurant", ignore = true)
    Menu toMenu(MenuDto menuDto);
}
