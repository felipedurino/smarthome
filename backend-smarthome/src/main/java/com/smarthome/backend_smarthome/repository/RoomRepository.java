package com.smarthome.backend_smarthome.repository;

import com.smarthome.backend_smarthome.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByResidenceId(Long residenceId);
    List<Room> findByResidenceId(Long residenceId);
}
