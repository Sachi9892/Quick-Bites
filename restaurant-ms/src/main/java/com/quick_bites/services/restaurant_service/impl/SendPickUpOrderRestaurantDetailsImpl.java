package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.ISendPickUpOrderRestaurantDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SendPickUpOrderRestaurantDetailsImpl implements ISendPickUpOrderRestaurantDetails {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantPickOrderDetailsDto sendDetails(Long restId) {

        Restaurant rest = restaurantRepository.findById(restId).orElseThrow(() -> new RestaurantNotFoundException("No Rest"));

        return RestaurantPickOrderDetailsDto.builder()
                .restaurantName(rest.getRestaurantName())
                .pickUpAddress(rest.getLocation().getAddress())
                .restaurantLongitude(rest.getLocation().getLongitude())
                .restaurantLatitude(rest.getLocation().getLatitude())
                .build();
    }
}
