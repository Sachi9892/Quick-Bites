package com.quick_bites.dto.dishdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleDishResponseDto {

    private Long dishId;
    private Long restId;
    private String dishName;
    private Double price;
    private DishType dishType;
    private String dishPic;

}
