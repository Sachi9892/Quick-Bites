package com.quick_bites.dto.dish_dto;

import com.quick_bites.entity.DishType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class AddDishDto {

    private String dishName;

    private String category;

    private String restaurant;

    private String description;

    private float price;

    private DishType dishType;

    private String dishPic;



}
