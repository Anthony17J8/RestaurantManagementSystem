package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.entity.User;

@Mapper
public interface UserMapper {

    @Mapping(source = "roles", target = "roles", ignore = true)
    UserDto fromUser(User user);

    @Mapping(source = "roles", target = "roles", ignore = true)
    User toUser(UserDto userDto);
}
