package ru.icoltd.rvs.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "restaurant")
@ToString(exclude = "restaurant")
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @org.hibernate.annotations.Formula(value = "(select count(*) from votes v where v.menu_id = id)")
    private Long votesAmount;

    @Builder
    public Menu(Long id, String name, LocalDateTime date, Restaurant restaurant, Long votesAmount) {
        super(id);
        this.name = name;
        this.date = date;
        this.restaurant = restaurant;
        this.votesAmount = votesAmount;
    }
}
