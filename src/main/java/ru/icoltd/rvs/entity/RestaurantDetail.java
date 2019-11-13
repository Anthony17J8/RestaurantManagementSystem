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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "restaurant_details")
public class RestaurantDetail extends BaseEntity {

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "street")
    private String street;

    @NotNull
    @Column(name = "phone")
    @Pattern(regexp = "^\\+*(?:[0-9] ?){6,14}[0-9]$")
    private String phone;

    @NotNull
    @Column(name = "site")
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9-]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String site;

    @Builder
    public RestaurantDetail(Integer id, String country, String city, String street, String phone, String site) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.site = site;
    }
}
