package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {
    boolean existsByUserId(Long userId);
    Optional<Residence> findByUserId(Long userId);
}
