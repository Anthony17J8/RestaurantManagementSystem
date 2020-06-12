package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.ReviewDto;
import ru.icoltd.rvs.dtos.UserDto;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.entity.User;

@Mapper
public interface ReviewMapper {

    @Mapping(source = "restaurant", target = "restaurant", ignore = true)
    ReviewDto fromReview(Review review);

    Review toReview(ReviewDto reviewDto);

    @Mapping(source = "roles", target = "roles", ignore = true)
    UserDto fromUser(User user);

    @Mapping(source = "roles", target = "roles", ignore = true)
    User toUser(UserDto userDto);
}
