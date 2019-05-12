package ru.icoltd.rvs.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_detail_id")
    private RestaurantDetail restaurantDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(String name, RestaurantDetail restaurantDetail) {
        this.name = name;
        this.restaurantDetail = restaurantDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantDetail getRestaurantDetail() {
        return restaurantDetail;
    }

    public void setRestaurantDetail(RestaurantDetail address) {
        this.restaurantDetail = address;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurantDetail=" + restaurantDetail +
                '}';
    }
}
