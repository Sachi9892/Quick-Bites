package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import org.springframework.data.domain.Pageable;

public interface RestaurantOverview {

    RestaurantOverViewDto getOverView(String name , Pageable pageable);
}
