package com.quick_bites.service.managers.order_manager.order_service.schedule_order;


import com.quick_bites.dto.orderdto.TimeSlotDto;
import com.quick_bites.entity.TimeSlot;
import com.quick_bites.exceptions.SlotAlreadyExistsException;
import com.quick_bites.repository.TimeSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;


    //Activate time slot
    public void activateTimeSlot(Long slotId) {

        TimeSlot timeSlot = timeSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found with id: " + slotId));

        timeSlot.setActive(true);
        timeSlotRepository.save(timeSlot);
    }


    public List<TimeSlot> createSlots(TimeSlotDto requestDto) {

        List<LocalTime> slotTimes = requestDto.getSlotTimes();

        return slotTimes.stream().map(slotTime -> {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setSlotTime(slotTime);

            return timeSlotRepository.save(timeSlot);
        }).toList();
    }



    //Deactivate time slot
    public void deactivateTimeSlot(Long slotId) {

        TimeSlot timeSlot = timeSlotRepository.findById(slotId)
                .orElseThrow(() -> new IllegalArgumentException("Slot not found with id: " + slotId));

        timeSlot.setActive(false);

        timeSlotRepository.save(timeSlot);

    }



    //Get all time slots
    public List<LocalTime> getAvailableSlots() {

        return timeSlotRepository.findByIsActiveTrue()
                .stream()
                .map(TimeSlot::getSlotTime).toList();

    }

}
