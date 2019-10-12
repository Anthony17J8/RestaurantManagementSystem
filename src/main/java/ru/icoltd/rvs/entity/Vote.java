package ru.icoltd.rvs.entity;

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
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "date")
    private LocalDateTime dateTime;

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public Vote(Menu menu, LocalDateTime dateTime) {
        this.menu = menu;
        this.dateTime = dateTime;
    }

    public Vote(User user, Menu menu, LocalDateTime dateTime) {
        this.user = user;
        this.menu = menu;
        this.dateTime = dateTime;
    }
}
