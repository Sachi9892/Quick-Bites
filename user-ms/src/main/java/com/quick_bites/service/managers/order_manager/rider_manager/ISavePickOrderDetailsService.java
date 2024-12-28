package com.quick_bites.service.managers.order_manager.rider_manager;

import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.entity.PickOrderDetails;

public interface ISavePickOrderDetailsService {

     PickOrderDetails savePickOrderDetails(
            Long orderId,
            Long restId,
            Long userId,
            Double userLatitude,
            Double userLongitude,
            RestaurantPickOrderDetailsDto restaurantDetails
    );
}
