package com.quick_bites.dto.restaurant_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AddRestaurantDto {

    private String restaurantName;
    private String mobileNumber;
    private String restPic;
    private String address;

}
