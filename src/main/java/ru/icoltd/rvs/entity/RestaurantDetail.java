package ru.icoltd.rvs.entity;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_detail")
public class RestaurantDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private int id;

    @JoinColumn(name = "city")
    private String city;

    @JoinColumn(name = "street")
    private String street;

    @JoinColumn(name = "phone")
    private String phone;

    @JoinColumn(name = "email")
    private String email;

    public RestaurantDetail() {
    }

    public RestaurantDetail(String city, String street, String phone, String email) {
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
