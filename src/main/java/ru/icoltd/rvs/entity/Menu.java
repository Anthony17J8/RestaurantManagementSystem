package ru.icoltd.rvs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"dishes", "votes"})
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Dish> dishes;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Vote> votes;

    @org.hibernate.annotations.Formula(value = "(select count(*) from votes v where v.menu_id = id)")
    private Long votesAmount;

    @org.hibernate.annotations.Formula(value = "(select sum(d.price) from dishes d where d.menu_id = id)")
    private BigDecimal totalAmount;

    @Builder
    public Menu(Integer id, String name, LocalDateTime date, Restaurant restaurant, List<Dish> dishes, List<Vote> votes,
                Long votesAmount, BigDecimal totalAmount) {
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
