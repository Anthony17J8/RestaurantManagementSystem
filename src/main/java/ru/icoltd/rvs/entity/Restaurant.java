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
@AllArgsConstructor
public class Restaurant extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private RestaurantDetail restaurantDetail;

    @Builder
    protected Restaurant(Long id, String name, String description, RestaurantDetail restaurantDetail) {
        super(id);
        this.name = name;
        this.description = description;
        this.restaurantDetail = restaurantDetail;
    }
}
