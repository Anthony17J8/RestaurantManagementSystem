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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"menu", "user"})
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "date")
    private LocalDateTime dateTime;

    @Builder
    public Vote(User user, Menu menu, LocalDateTime dateTime) {
        this.user = user;
        this.menu = menu;
        this.dateTime = dateTime;
    }
}
