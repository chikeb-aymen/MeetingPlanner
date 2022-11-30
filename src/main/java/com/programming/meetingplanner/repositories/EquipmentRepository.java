package com.programming.meetingplanner.repositories;

import com.programming.meetingplanner.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
}
