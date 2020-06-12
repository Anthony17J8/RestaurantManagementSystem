package ru.icoltd.rvs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.icoltd.rvs.dtos.RestaurantDetailDto;
import ru.icoltd.rvs.dtos.RestaurantDto;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;

@Mapper
public interface RestaurantMapper {

    RestaurantDto fromRestaurant(Restaurant restaurant);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "site", target = "url")
    RestaurantDetailDto fromRestaurantDetail(RestaurantDetail restaurantDetail);

    Restaurant toRestaurant(RestaurantDto restaurantDto);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "url", target = "site")
    RestaurantDetail toRestaurantDetail(RestaurantDetailDto restaurantDetailDto);
}
