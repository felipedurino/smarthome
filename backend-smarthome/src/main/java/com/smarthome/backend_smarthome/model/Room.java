package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
@Getter
@Setter

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_name", length = 100, nullable = false)
    private String roomName;
    @Column(name = "room_number", length = 100, nullable = true)
    private int roomNumber;
    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residence;

}
