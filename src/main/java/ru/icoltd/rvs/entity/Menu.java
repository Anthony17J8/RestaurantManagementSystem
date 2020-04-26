package ru.icoltd.rvs.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "restaurant")
@ToString(exclude = "restaurant")
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @org.hibernate.annotations.Formula(value = "(select count(*) from votes v where v.menu_id = id)")
    private Long votesAmount;

    @Builder
    public Menu(String name, LocalDateTime date, Restaurant restaurant, Long votesAmount) {
        this.name = name;
        this.date = date;
        this.restaurant = restaurant;
        this.votesAmount = votesAmount;
    }
}
