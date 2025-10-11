package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "residences")
@Data
@NoArgsConstructor
public class Residence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "street", length = 255, nullable = false)
    private String street;
    
    @Column(name = "house_number", length = 10, nullable = false)
    private String houseNumber;
    
    @Column(name = "zip_code", length = 8, nullable = false)
    private String zipCode;
    
    @Column(name = "state", length = 2, nullable = false)
    private String state;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
