package com.smarthome.backend_smarthome.DTOS;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private long cpf;
    private LocalDate dt_de_nascimento;
}
