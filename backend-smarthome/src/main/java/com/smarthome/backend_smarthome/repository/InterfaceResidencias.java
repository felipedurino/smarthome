package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Residencias;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InterfaceResidencias extends CrudRepository<Residencias, Long> {
    boolean existsByUsuario_id(Long idUsuario);
    Optional<Residencias> findByUsuario_Id(Long idUsuario);

}
