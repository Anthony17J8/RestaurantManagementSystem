package ru.icoltd.rvs.util;

import com.github.javafaker.Faker;
import ru.icoltd.rvs.dtos.*;
import ru.icoltd.rvs.entity.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockDataUtils {

    public static final Long ID = 1L;

    private static final Faker FAKER = new Faker();

    private MockDataUtils() {
    }

    public static Restaurant getMockRestaurant() {
        return Restaurant.builder()
                .name(FAKER.company().name())
                .description(String.join("", FAKER.lorem().paragraphs(4)))
                .restaurantDetail(getMockRestaurantDetail())
                .build();
    }

    public static RestaurantDto getMockRestaurantDto() {
        Restaurant rMock = withId(getMockRestaurant());
        RestaurantDetail dMock = getMockRestaurantDetail();
        return RestaurantDto.builder()
                .id(rMock.getId())
                .name(rMock.getName())
                .description(rMock.getDescription())
                .restaurantDetail(
                        RestaurantDetailDto.builder()
                                .city(dMock.getCity())
                                .country(dMock.getCountry())
                                .phoneNumber(dMock.getPhone())
                                .street(dMock.getStreet())
                                .url(dMock.getSite()).build()
                ).build();
    }

    public static List<RestaurantDto> getMockRestaurantDtos(int count) {
        return Stream.generate(MockDataUtils::getMockRestaurantDto).limit(count).collect(Collectors.toList());
    }

    public static List<Restaurant> getMockRestaurants(int count) {
        return Stream.generate(MockDataUtils::getMockRestaurant).limit(count).collect(Collectors.toList());
    }

    public static RestaurantDetail getMockRestaurantDetail() {
        return RestaurantDetail.builder()
                .city(FAKER.address().city())
                .country(FAKER.address().country())
                .site(FAKER.internet().url())
                .phone(FAKER.regexify("[1-9]{11}"))
                .street(FAKER.address().streetAddress())
                .build();
    }

    public static Menu getMockMenu() {
        return Menu.builder()
                .name(FAKER.funnyName().name())
                .date(LocalDateTime.now())
                .votesAmount(FAKER.number().randomNumber())
                .build();
    }

    public static List<Menu> getMockMenus(int count) {
        return Stream.generate(MockDataUtils::getMockMenu).limit(count).collect(Collectors.toList());
    }

    public static MenuDto getMockMenuDto() {
        Menu menu = withId(getMockMenu());
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .date(menu.getDate())
                .votesAmount(menu.getVotesAmount())
                .build();
    }

    public static List<MenuDto> getMockMenuDtos(int count) {
        return Stream.generate(MockDataUtils::getMockMenuDto).limit(count).collect(Collectors.toList());
    }

    public static Review getMockReview() {
        return Review.builder()
                .createdAt(LocalDateTime.now())
                .text(FAKER.funnyName().name())
                .build();
    }

    public static List<Review> getMockReviews(int count) {
        return Stream.generate(MockDataUtils::getMockReview).limit(count).collect(Collectors.toList());
    }

    public static List<Dish> getMockDishes(int count) {
        return Stream.generate(MockDataUtils::getMockDish).limit(count).collect(Collectors.toList());
    }

    public static Dish getMockDish() {
        return Dish.builder()
                .description(FAKER.funnyName().name())
                .price(FAKER.number().randomDouble(3, 0, Integer.MAX_VALUE))
                .build();
    }

    public static DishDto getMockDishDto() {
        Dish dish = withId(getMockDish());
        return DishDto.builder()
                .id(dish.getId())
                .description(dish.getDescription())
                .price(dish.getPrice())
                .build();
    }

    public static List<DishDto> getMockDishDtos(int count) {
        return Stream.generate(MockDataUtils::getMockDishDto).limit(count).collect(Collectors.toList());
    }

    public static <T extends BaseEntity> T withId(T obj) {
        obj.setId(FAKER.number().randomNumber());
        return obj;
    }

    public static User getMockUser() {
        return User.builder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .username(FAKER.name().username())
                .password(FAKER.internet().password())
                .email(FAKER.internet().emailAddress())
                .dateOfBirth(new Date(System.currentTimeMillis()))
                .build();
    }

    public static UserDto getMockUserDto() {
        User user = withId(getMockUser());
        return UserDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .password(user.getPassword()).build();
    }

    public static Vote getMockVote() {
        return Vote.builder().dateTime(LocalDateTime.now()).build();
    }

    public static VoteDto getMockVoteDto() {
        return VoteDto.builder().dateTime(LocalDateTime.now()).build();
    }

    public static List<Role> getMockRoles(int count) {
        return Stream.generate(MockDataUtils::getMockRole).limit(count).collect(Collectors.toList());
    }

    public static Role getMockRole() {
        return Role.builder()
                .name(FAKER.company().profession())
                .build();
    }
}
