package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;

public interface IRestaurantOverview {

    RestaurantOverViewDto getOverView(String name);

}
