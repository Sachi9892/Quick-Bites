package com.quick_bites.controllers.order_controller;

import com.quick_bites.service.managers.order_manager.order_service.schedule_order.SlotTimingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class ScheduleSlotController {

    private final SlotTimingService slotTimingService;

    @GetMapping("/schedule/slots")

    public ResponseEntity<List<LocalTime>> getAvailableSlots() {
        List<LocalTime> availableSlots = slotTimingService.getAvailableSlots();
        return ResponseEntity.ok(availableSlots);
    }

}
