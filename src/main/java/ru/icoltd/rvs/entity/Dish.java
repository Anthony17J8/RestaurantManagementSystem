package ru.icoltd.rvs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity{

    @NotNull
    @Column(name = "description")
    private String description;

    @PositiveOrZero
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Builder
    public Dish(Integer id, @NotNull String description, @PositiveOrZero double price, Menu menu) {
        super(id);
        this.description = description;
        this.price = price;
        this.menu = menu;
    }
}
