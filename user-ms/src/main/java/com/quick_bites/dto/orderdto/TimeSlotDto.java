package com.quick_bites.dto.orderdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor  @NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSlotDto implements Serializable {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private List<LocalTime> slotTimes;

}
