package com.smarthome.backend_smarthome.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lights")
@Data
@NoArgsConstructor
public class Light {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "is_on", nullable = false)
    private boolean isOn;
    
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
