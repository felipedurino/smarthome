package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InterfaceUsuario extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
