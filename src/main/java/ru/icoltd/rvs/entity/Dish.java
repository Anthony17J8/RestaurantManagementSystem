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
    public Dish(Long id, String description, double price, Menu menu) {
        super(id);
        this.description = description;
        this.menu = menu;
        this.price = price;
    }
}
