package com.smarthome.backend_smarthome.controller.api;

import com.smarthome.backend_smarthome.repository.LightRepository;
import com.smarthome.backend_smarthome.repository.RoomRepository;
import com.smarthome.backend_smarthome.model.Light;
import com.smarthome.backend_smarthome.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lights")
public class LightController {

    @Autowired
    private LightRepository lightRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Light> getAllLights() {
        return lightRepository.findAll();
    }

    @PostMapping
    public Light createLight(@RequestBody Light light) {
        Long roomId = light.getRoom().getId();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found."));
        light.setRoom(room);
        return lightRepository.save(light);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Light>> getLightsByRoom(@PathVariable Long roomId) {
        List<Light> lights = lightRepository.findByRoomId(roomId);
        return ResponseEntity.ok(lights);
    }

    @PutMapping("/{lightId}/toggle")
    public ResponseEntity<Light> toggleLight(@PathVariable Long lightId) {
        Light light = lightRepository.findById(lightId)
                .orElseThrow(() -> new RuntimeException("Light with ID " + lightId + " not found."));
        
        light.setOn(!light.isOn());
        Light updatedLight = lightRepository.save(light);
        return ResponseEntity.ok(updatedLight);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Light>> getLightsByUser(@PathVariable Long userId) {
        List<Light> lights = lightRepository.findByRoomResidenceUserId(userId);
        return ResponseEntity.ok(lights);
    }
}
