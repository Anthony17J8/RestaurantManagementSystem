package ru.icoltd.rvs.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "restaurant")
@Entity
@Builder
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Lob
    @Column(name = "review_text")
    private String text;

    @Column(name = "created_at")
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Review(Long id, String text, Restaurant restaurant, User user) {
        super(id);
        this.text = text;
        this.restaurant = restaurant;
        this.user = user;
    }
}
