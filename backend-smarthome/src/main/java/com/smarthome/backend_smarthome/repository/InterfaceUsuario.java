package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface InterfaceUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
