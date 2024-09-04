package com.quick_bites.dto.cartdto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RemoveFromCartDto {

    private Long userId;
    private Long dishId;

}
