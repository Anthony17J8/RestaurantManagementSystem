package ru.icoltd.rvs.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"reviews"})
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    private RestaurantDetail restaurantDetail;

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @Builder
    public Restaurant(String name, RestaurantDetail restaurantDetail,
                      Set<Menu> menus, Set<Review> reviews) {
        this.name = name;
        this.restaurantDetail = restaurantDetail;
//        this.menus = menus;
        this.reviews = reviews;
    }
//
//    public void addMenu(Menu menu) {
//        if (menu.isNew()) {
//            this.menus.add(menu);
//        }
//        menu.setRestaurant(this);
//    }

//    public void removeMenu(Menu menu) {
//        menus.remove(menu);
//        menu.setRestaurant(null);
//    }
}
