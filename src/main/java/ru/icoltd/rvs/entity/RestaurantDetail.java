package ru.icoltd.rvs.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurant_details")
public class RestaurantDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int detailId;

    @NotNull(message = "Field is required")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Field is required")
    @Column(name = "city")
    private String city;

    @NotNull(message = "Field is required")
    @Column(name = "street")
    private String street;

    @NotNull(message = "Field is required")
    @Column(name = "phone")
    private String phone;

    @Column(name = "site")
    private String email;

    public RestaurantDetail() {
    }

    public RestaurantDetail(String country, String city, String street, String phone, String email) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.email = email;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int id) {
        this.detailId = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RestaurantDetail{" +
                "id=" + detailId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
