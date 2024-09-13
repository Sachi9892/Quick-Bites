package com.quick_bites.controllers.order_controller.schedule_order;


import com.quick_bites.dto.orderdto.TimeSlotDto;
import com.quick_bites.entity.TimeSlot;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.TimeSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/slots")
@AllArgsConstructor
public class SlotTimerController {

    private final TimeSlotService timeSlotService;

    @PostMapping("/create")
    public ResponseEntity<List<TimeSlot>> createSlots(@RequestBody TimeSlotDto requestDto) {
        List<TimeSlot> slots = timeSlotService.createSlots(requestDto);
        return ResponseEntity.ok(slots);
    }



    // Endpoint to fetch all active time slots
    @GetMapping("/available")
    public ResponseEntity<List<LocalTime>> getAvailableSlots() {
        List<LocalTime> availableSlots = timeSlotService.getAvailableSlots();
        return new ResponseEntity<>(availableSlots, HttpStatus.OK);
    }



    // Endpoint to deactivate a time slot
    @PutMapping("/deactivate")
    public ResponseEntity<String> deactivateTimeSlot(@RequestParam Long slotId) {
        timeSlotService.deactivateTimeSlot(slotId);
        return new ResponseEntity<>("Time slot deactivated successfully", HttpStatus.OK);
    }



    // Endpoint to activate a time slot
    @PutMapping("/activate")
    public ResponseEntity<String> activateTimeSlot(@RequestParam Long slotId) {
        timeSlotService.activateTimeSlot(slotId);
        return new ResponseEntity<>("Time slot activated successfully", HttpStatus.OK);
    }


}
