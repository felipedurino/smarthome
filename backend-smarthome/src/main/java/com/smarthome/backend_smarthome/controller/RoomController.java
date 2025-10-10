package com.smarthome.backend_smarthome.controller;

import com.smarthome.backend_smarthome.repository.RoomRepository;
import com.smarthome.backend_smarthome.repository.ResidenceRepository;
import com.smarthome.backend_smarthome.model.Room;
import com.smarthome.backend_smarthome.model.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ResidenceRepository residenceRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @PostMapping
    public List<Room> createRooms(@RequestBody List<Room> rooms) {
        return rooms.stream().map(room -> {
            Long residenceId = room.getResidence().getId();
            Residence residence = residenceRepository.findById(residenceId)
                    .orElseThrow(() -> new RuntimeException("Residence with ID " + residenceId + " not found."));
            room.setResidence(residence);
            return roomRepository.save(room);
        }).collect(Collectors.toList());
    }
    
    @GetMapping("/residence/{residenceId}")
    public ResponseEntity<List<Room>> getRoomsByResidence(@PathVariable Long residenceId) {
        List<Room> rooms = roomRepository.findByResidenceId(residenceId);
        return ResponseEntity.ok(rooms);
    }
}
