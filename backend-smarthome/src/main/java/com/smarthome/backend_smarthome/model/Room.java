package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "room_name", length = 100, nullable = false)
    private String roomName;
    
    @Column(name = "room_number", nullable = true)
    private Integer roomNumber;
    
    @ManyToOne
    @JoinColumn(name = "residence_id", nullable = false)
    private Residence residence;
}
