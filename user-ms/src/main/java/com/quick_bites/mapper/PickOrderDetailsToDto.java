package com.quick_bites.mapper;

import com.quick_bites.dto.confirm_order_dto.PickOrderDetailsDto;
import com.quick_bites.entity.PickOrderDetails;

public class PickOrderDetailsToDto {

    private PickOrderDetailsToDto() {

    }

    public static PickOrderDetailsDto from(PickOrderDetails details) {

        return PickOrderDetailsDto.builder()
                .restaurantAddress(details.getPickUpAddress())
                .restaurantName(details.getRestaurantName())
                .restaurantLongitude(details.getRestaurantLongitude())
                .restaurantLatitude(details.getRestaurantLatitude())
                .userLatitude(details.getUserLatitude())
                .userLongitude(details.getUserLongitude())
                .orderId(details.getOrderId())
                .build();

    }
}
