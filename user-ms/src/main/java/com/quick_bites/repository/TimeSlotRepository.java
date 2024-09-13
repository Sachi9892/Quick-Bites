package com.quick_bites.repository;

import com.quick_bites.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot , Long> {

    List<TimeSlot> findByIsActiveTrue();  // Fetch only active time slots

    boolean existsBySlotTime(LocalTime slotTime);
}
