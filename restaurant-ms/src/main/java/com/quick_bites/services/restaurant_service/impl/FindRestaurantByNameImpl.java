package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.restaurant_dto.ResponseRestDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.mapper.RestaurantMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindRestaurantByName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FindRestaurantByNameImpl implements IFindRestaurantByName {

    private final RestaurantRepository restaurantRepository;

    @Override
    public ResponseRestDto findRestaurant(String restName) {

        Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantName(restName);

        if(restaurant.isEmpty()) {
            throw new ResourceNotFoundException("No restaurant found with name : " + restName);
        }

        return RestaurantMapper.mapToDto(restaurant);


    }
}
