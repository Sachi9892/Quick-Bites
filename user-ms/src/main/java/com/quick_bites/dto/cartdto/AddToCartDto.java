package com.quick_bites.dto.cartdto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class AddToCartDto implements Serializable {

    private Long userId;

    private Long dishId;

}
