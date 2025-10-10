package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
    List<Light> findByRoomId(Long roomId);
    List<Light> findByRoomResidenceUserId(Long userId);
}
