package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lights")
@Getter
@Setter

public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "is_on", nullable = false)
    private boolean isOn;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
