package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.restaurant_dto.ResponseRestDto;

public interface FindRestaurantByName {

    ResponseRestDto findRestaurant(String restName);
}
