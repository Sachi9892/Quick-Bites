package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;

public interface ISendPickUpOrderRestaurantDetails {

    RestaurantPickOrderDetailsDto sendDetails(Long restId);

}
