package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Comodos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterfaceComodo extends CrudRepository<Comodos, Long> {
    boolean existsByResidencia_id(Long idResidencia);
    List<Comodos> findByResidenciaId(Long idResidencia);

}
