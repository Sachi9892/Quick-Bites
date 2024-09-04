package com.quick_bites.dto.restaurant_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseRestDto {

    private String restaurantName;
    private String mobileNumber;
    private Double avgReview;

}
