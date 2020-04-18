package ru.icoltd.rvs.entity;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "menu")
@ToString(exclude = "menu")
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String description;

    @PositiveOrZero
    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Builder
    public Dish(@NotNull String description, @PositiveOrZero double price) {
        this.description = description;
        this.price = price;
    }
}
