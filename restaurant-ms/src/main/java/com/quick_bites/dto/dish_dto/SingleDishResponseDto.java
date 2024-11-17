package com.quick_bites.dto.dish_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quick_bites.entity.DishType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleDishResponseDto implements Serializable {

    private Long dishId;
    private Long restId;

    private String dishName;
    private Double price;

    @Enumerated(EnumType.STRING)
    private DishType dishType;

    private String dishPic;

}
