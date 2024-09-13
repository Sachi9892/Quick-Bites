package com.quick_bites.dto.orderdto;

import lombok.*;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor  @NoArgsConstructor
@Getter @Setter
public class TimeSlotDto {

    private List<LocalTime> slotTimes;

}
