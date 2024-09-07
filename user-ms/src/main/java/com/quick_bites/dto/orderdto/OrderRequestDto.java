package com.quick_bites.dto.orderdto;


import com.quick_bites.entity.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    private Long cartId;
    private Long deliveryAddress;
    private OrderType orderType;

}
