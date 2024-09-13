package com.quick_bites.service.managers.order_manager.order_service.schedule_order;


import com.quick_bites.exceptions.SlotNotAvailableOrExpireException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeSlotVerificationService {

    private final TimeSlotService timeSlotService;

    // Ensure that the order is placed at least 30 minutes before the scheduled time
    public boolean verifyOrderTiming(LocalDateTime scheduledTime) {

        LocalTime selectedTime = scheduledTime.toLocalTime();

        // Ensure the scheduled time is at least 30 minutes in the future
        LocalDateTime now = LocalDateTime.now();
        if (scheduledTime.isBefore(now.plusMinutes(30))) {
            return false;
        }

        // Fetch available slots from the TimeSlotService
        List<LocalTime> availableSlots = timeSlotService.getAvailableSlots();

        // Check if the selected time matches one of the defined slots
        return availableSlots.stream().anyMatch(slot -> slot.equals(selectedTime));
    }



    // Method to throw an exception if verification fails
    public void validateOrderTiming(LocalDateTime scheduledTime) {

        if (!verifyOrderTiming(scheduledTime)) {
            throw new SlotNotAvailableOrExpireException("Scheduled time is either expired or invalid. Please choose a valid time slot!");
        }
    }


}
