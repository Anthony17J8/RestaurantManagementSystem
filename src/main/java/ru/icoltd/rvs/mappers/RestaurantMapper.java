package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.RestaurantDetailDto;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;

@Mapper
public interface RestaurantMapper {

    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "site", target = "url")
    RestaurantDetailDto restaurantDetailToRestaurantDetailDto(RestaurantDetail restaurantDetail);

    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "url", target = "site")
    RestaurantDetail restaurantDetailDtoToRestaurantDetail(RestaurantDetailDto restaurantDetailDto);

}
