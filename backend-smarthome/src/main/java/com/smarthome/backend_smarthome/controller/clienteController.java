package com.smarthome.backend_smarthome.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class clienteController {

    @GetMapping("/cliente")
    public String texto() {
        return "acessando a API";

    }
}