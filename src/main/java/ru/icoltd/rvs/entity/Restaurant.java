package ru.icoltd.rvs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"menus", "reviews"})
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    private RestaurantDetail restaurantDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Builder
    public Restaurant(Integer id, String name, RestaurantDetail restaurantDetail,
                      List<Menu> menus, List<Review> reviews) {
        super(id);
        this.name = name;
        this.restaurantDetail = restaurantDetail;
        this.menus = menus;
        this.reviews = reviews;
    }
}
