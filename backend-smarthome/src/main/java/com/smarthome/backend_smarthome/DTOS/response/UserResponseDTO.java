package com.smarthome.backend_smarthome.DTOS.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private Long identification;
    private LocalDate birthDate;
    private String email;
}
