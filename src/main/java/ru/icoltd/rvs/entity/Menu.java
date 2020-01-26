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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"dishes"})
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private Collection<Dish> dishes = new ArrayList<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE)
    private Collection<Vote> votes = new ArrayList<>();

    @org.hibernate.annotations.Formula(value = "(select count(*) from votes v where v.menu_id = id)")
    private Long votesAmount;

    @org.hibernate.annotations.Formula(value = "(select sum(d.price) from dishes d where d.menu_id = id)")
    private BigDecimal totalAmount;

    @Builder
    public Menu(Integer id, String name, LocalDate date,
                Restaurant restaurant, Collection<Dish> dishes,
                Collection<Vote> votes, Long votesAmount, BigDecimal totalAmount) {
        super(id);
        this.name = name;
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.votes = votes;
        this.votesAmount = votesAmount;
        this.totalAmount = totalAmount;
    }
}
