package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_name",length = 100, nullable = false)
    private String roomName;
    @Column(name = "room_number",length = 100, nullable = true)
    private int roomNumber;
    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
