package ru.icoltd.rvs.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "restaurant_details")
public class RestaurantDetail extends BaseEntity{

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
    @Pattern(regexp = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
            "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
            "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$")
    private String phone;

    @NotNull
    @Column(name = "site")
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String site;

    public RestaurantDetail() {
    }

    public RestaurantDetail(String country, String city, String street, String phone, String site) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.site = site;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String email) {
        this.site = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        RestaurantDetail that = (RestaurantDetail) o;

        return new EqualsBuilder()
                .append(country, that.country)
                .append(city, that.city)
                .append(street, that.street)
                .append(phone, that.phone)
                .append(site, that.site)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(country)
                .append(city)
                .append(street)
                .append(phone)
                .append(site)
                .toHashCode();
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
