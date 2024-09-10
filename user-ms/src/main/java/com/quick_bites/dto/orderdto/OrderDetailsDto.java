package com.quick_bites.dto.orderdto;


import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailsDto {

    private List<SingleDishResponseDto> dishes;
    private Double totalAmount;
    private LocalDateTime orderDate;

}
