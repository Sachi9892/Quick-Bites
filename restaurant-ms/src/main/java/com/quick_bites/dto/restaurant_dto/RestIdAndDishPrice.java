package com.quick_bites.dto.restaurant_dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestIdAndDishPrice {

    private Long restId;

    private Double price;

}
