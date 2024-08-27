package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.dto.restaurant_dto.AddRestaurantDto;
import com.quick_bites.entity.Location;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.location_service.GeoCodingService;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.AddRestaurant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class IAddRestaurant implements AddRestaurant {

    private final RestaurantRepository restaurantRepository;
    private final GeoCodingService geoCodingService;

    @Override
    public Restaurant addRestaurant(AddRestaurantDto addRestaurantDto) {

        LocationDto coordinates = geoCodingService.getCoordinates(addRestaurantDto.getAddress());

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantName(addRestaurantDto.getRestaurantName());
        restaurant.setMobileNumber(addRestaurantDto.getMobileNumber());
        restaurant.setRestPic(addRestaurantDto.getRestPic());
        restaurant.setLocation(new Location(coordinates.getLatitude(), coordinates.getLongitude(), addRestaurantDto.getAddress()));

        return restaurantRepository.save(restaurant);

    }
}
