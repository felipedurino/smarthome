package com.smarthome.backend_smarthome.controller.api;

import com.smarthome.backend_smarthome.repository.ResidenceRepository;
import com.smarthome.backend_smarthome.repository.UserRepository;
import com.smarthome.backend_smarthome.model.Residence;
import com.smarthome.backend_smarthome.model.User;
import com.smarthome.backend_smarthome.service.serviceImpl.ResidenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/residences")
public class ResidenceController {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private ResidenceServiceImpl residenceService;



    @GetMapping
    public List<Residence> getAllResidences() {

        return residenceRepository.findAll();
    }

    @PostMapping
    public ResponseEntity <Residence> createResidence(@RequestBody Residence residence) {
        try {
            Residence savedResidence = residenceService.createResidence(residence);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedResidence);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/check/{userId}")
    public ResponseEntity<Boolean> checkResidenceExists(@PathVariable Long userId) {
        boolean hasResidence = residenceRepository.existsByUserId(userId);
        return ResponseEntity.ok(hasResidence);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Residence> getResidenceByUser(@PathVariable Long userId) {
        return residenceRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
