package com.quick_bites.dto.dishdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
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
