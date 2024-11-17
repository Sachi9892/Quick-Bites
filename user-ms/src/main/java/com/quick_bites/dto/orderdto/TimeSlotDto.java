package com.quick_bites.dto.orderdto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor  @NoArgsConstructor
@Getter @Setter
public class TimeSlotDto {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private List<LocalTime> slotTimes;

}
