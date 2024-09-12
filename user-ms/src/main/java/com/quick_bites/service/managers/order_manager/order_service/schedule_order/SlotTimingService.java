package com.quick_bites.service.managers.order_manager.order_service.schedule_order;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


@Getter
@Service
public class SlotTimingService {

    // Get available slots for reference or display
    private final List<LocalTime> availableSlots = Arrays.asList(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0)
    );


    // Check if the selected time is a valid slot
    public boolean isValidSlot(LocalDateTime scheduledTime) {
        LocalTime selectedTime = scheduledTime.toLocalTime();

        // Ensure the selected time is at least 30 minutes in the future
        LocalDateTime now = LocalDateTime.now();
        if (scheduledTime.isBefore(now.plusMinutes(30))) {
            return false;
        }

        // Check if the selected time matches one of the defined slots
        return availableSlots.stream().anyMatch(slot -> slot.equals(selectedTime));
    }


}
