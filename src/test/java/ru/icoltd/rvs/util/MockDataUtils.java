package ru.icoltd.rvs.util;

import com.github.javafaker.Faker;
import ru.icoltd.rvs.entity.BaseEntity;
import ru.icoltd.rvs.entity.Dish;
import ru.icoltd.rvs.entity.Menu;
import ru.icoltd.rvs.entity.Restaurant;
import ru.icoltd.rvs.entity.RestaurantDetail;
import ru.icoltd.rvs.entity.Review;
import ru.icoltd.rvs.entity.Vote;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockDataUtils {

    private static final Faker FAKER = new Faker();

    private MockDataUtils() {
    }

    public static Restaurant getMockRestaurant() {
        return Restaurant.builder()
                .name(FAKER.company().name())
                .restaurantDetail(getMockRestaurantDetail())
                .build();
    }

    public static List<Restaurant> getMockRestaurants(int count) {
        return Stream.generate(MockDataUtils::getMockRestaurant).limit(count).collect(Collectors.toList());
    }

    public static RestaurantDetail getMockRestaurantDetail() {
        return RestaurantDetail.builder()
                .city(FAKER.address().city())
                .country(FAKER.address().country())
                .site(FAKER.internet().domainName())
                .phone(FAKER.regexify("[1-9]{11}"))
                .street(FAKER.address().streetAddress())
                .build();
    }

    public static Menu getMockMenu() {
        return Menu.builder()
                .name(FAKER.funnyName().name())
                .date(ZonedDateTime.now())
                .build();
    }

    public static List<Menu> getMockMenus(int count) {
        return Stream.generate(MockDataUtils::getMockMenu).limit(count).collect(Collectors.toList());
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

    public static <T extends BaseEntity> T withId(T obj) {
        obj.setId(Long.valueOf(FAKER.number().randomNumber()).intValue());
        return obj;
    }

    public static Vote getMockVote() {
        return Vote.builder().dateTime(LocalDateTime.now()).build();
    }
}
