package com.smarthome.backend_smarthome.DTOS.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String email;

    public LoginResponseDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }

}
