package com.smarthome.backend_smarthome.service.serviceImpl;

import com.smarthome.backend_smarthome.model.Residence;
import com.smarthome.backend_smarthome.model.User;
import com.smarthome.backend_smarthome.repository.ResidenceRepository;
import com.smarthome.backend_smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResidenceServiceImpl {
    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private UserRepository userRepository;

    public Residence createResidence(Residence residence){
        User user= userRepository.findById(residence.getUser().getId())
                .orElseThrow(() -> new RuntimeException(" ID " + residence.getUser().getId() + " nao foi encontrado."));
        if(!isValidZipCode(residence.getZipCode())) {
            throw new IllegalArgumentException("CEP invalido");
        }

        return residenceRepository.save(residence);
    }
    private boolean isValidZipCode(String zipCode) {
        if(zipCode==null || zipCode.isEmpty()) {
            return false;
        }
        return zipCode.matches("\\d{8}");

    }

}
