package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "residences")
@Getter
@Setter

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

}
