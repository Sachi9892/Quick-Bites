package com.quick_bites.dto.dishdto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleDishResponseDto {

    private Long dishId;
    private Long restId;

    private String dishName;
    private Double price;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String dishPic;

}
