package com.quick_bites.dto.dish_dto;

import com.quick_bites.entity.DishType;
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
