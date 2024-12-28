package com.quick_bites.dto.confirm_order_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PickOrderDetailsDto {

    private Long orderId;

    private String restaurantName;
    private String restaurantAddress;
    private Double restaurantLatitude;
    private Double restaurantLongitude;

    private Double userLatitude;
    private Double userLongitude;


}
