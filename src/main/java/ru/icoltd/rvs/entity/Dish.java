package ru.icoltd.rvs.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "menu")
@ToString(exclude = "menu")
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Builder
    public Dish(String description, double price, Menu menu) {
        this.description = description;
        this.menu = menu;
        this.price = price;
    }
}
