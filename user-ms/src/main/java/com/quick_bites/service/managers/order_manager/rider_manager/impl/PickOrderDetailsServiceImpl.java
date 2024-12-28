package com.quick_bites.service.managers.order_manager.rider_manager.impl;

import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.entity.PickOrderDetails;
import com.quick_bites.repository.PickUpOrderDetailsRepository;
import com.quick_bites.service.managers.order_manager.rider_manager.ISavePickOrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PickOrderDetailsServiceImpl implements ISavePickOrderDetailsService {

    private final PickUpOrderDetailsRepository pickUpOrderDetailsRepository;

    @Override
    public PickOrderDetails savePickOrderDetails( Long orderId,
                                      Long restId,
                                      Long userId,
                                      Double userLatitude,
                                      Double userLongitude,
                                      RestaurantPickOrderDetailsDto restaurantDetails)
    {

        PickOrderDetails details = PickOrderDetails.builder()
                .orderId(orderId)
                .userId(userId)
                .restaurantId(restId)
                .pickUpAddress(restaurantDetails.getPickUpAddress())
                .restaurantName(restaurantDetails.getRestaurantName())
                .restaurantLatitude(restaurantDetails.getRestaurantLatitude())
                .restaurantLongitude(restaurantDetails.getRestaurantLongitude())
                .userLatitude(userLatitude)
                .userLongitude(userLongitude)
                .build();

        return pickUpOrderDetailsRepository.save(details);
    }
}
