package com.quick_bites.dto.orderdto;


import com.quick_bites.entity.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequestDto {

    private Long cartId;
    private Long deliveryAddress;
    private OrderType orderType;
    private LocalTime scheduledTime;

}
