package com.quick_bites.dto.cartdto;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class AddToCartDto {

    private Long dishId;

    private Long userId;

    private Double price;
}
