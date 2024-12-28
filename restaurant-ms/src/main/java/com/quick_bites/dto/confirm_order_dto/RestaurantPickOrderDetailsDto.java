package com.quick_bites.dto.confirm_order_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantPickOrderDetailsDto {

    private String restaurantName;
    private String pickUpAddress; //Basically a restaurant Address
    private Double restaurantLatitude;
    private Double restaurantLongitude;

}
