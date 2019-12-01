package ru.icoltd.rvs.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Embeddable
@Builder
public class RestaurantDetail {

    @NotNull
    @Column(nullable = false)
    private String country;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String street;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^\\+*(?:[0-9] ?){6,14}[0-9]$")
    private String phone;

    @NotNull
    @Column(nullable = false)
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www\\.)?[a-z0-9]+[-]?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String site;
}
