package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "residences")

public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "house_number",length = 1000, nullable = false)
    private String houseNumber;
    @Column(name = "zip_code",length = 8, nullable = false)
    private String zipCode;
    @Column(name = "state", length = 2, nullable = false)
    private String state;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getState() {return state; }

    public void setState(String state) {this.state = state; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
