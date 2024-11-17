package com.quick_bites.dto.orderdto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {

    private List<SingleDishResponseDto> dishes;
    private Double totalAmount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime orderDate;

}
