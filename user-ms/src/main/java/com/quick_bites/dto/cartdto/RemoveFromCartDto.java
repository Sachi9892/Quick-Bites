package com.quick_bites.dto.cartdto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class RemoveFromCartDto implements Serializable {

    private Long userId;
    private Long dishId;

}
