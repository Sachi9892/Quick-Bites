package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.restaurant_dto.AddRestaurantDto;
import com.quick_bites.entity.Restaurant;

public interface IAddRestaurant {

    Restaurant addRestaurant(AddRestaurantDto restaurantDto);

}
