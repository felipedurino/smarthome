package com.smarthome.backend_smarthome.DTOS.request;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
