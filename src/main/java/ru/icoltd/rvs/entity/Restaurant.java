package ru.icoltd.rvs.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    @Column(name = "name")
    private String name;

    private RestaurantDetail restaurantDetail;

    @Builder
    public Restaurant(String name, RestaurantDetail restaurantDetail) {
        this.name = name;
        this.restaurantDetail = restaurantDetail;
    }
}
