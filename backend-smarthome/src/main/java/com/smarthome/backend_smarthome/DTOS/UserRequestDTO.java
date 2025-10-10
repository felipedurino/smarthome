package com.smarthome.backend_smarthome.DTOS;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequestDTO {
    private String name;
    private Long identification;
    private LocalDate birthDate;
    private String email;
    private String password;
}