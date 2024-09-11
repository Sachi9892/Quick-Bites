package com.quick_bites.dto.orderdto;


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
    private LocalDateTime orderDate;

}
