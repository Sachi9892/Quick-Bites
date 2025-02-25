package com.quick_bites.services.dishservice_public.impl;


import com.quick_bites.entity.Dish;
import com.quick_bites.location_service.ICalculateDistanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DistanceFilterService {

    private final ICalculateDistanceService calculateDistanceService;

    public List<Dish> filterByDistance(List<Dish> dishes, Double userLatitude, Double userLongitude, Double minDistance, Double maxDistance) {
        if (userLatitude == null || userLongitude == null || (minDistance == null && maxDistance == null)) return dishes;
        List<Dish> list = dishes.stream()
                .filter(dish -> {
                    double restaurantLatitude = dish.getRestaurant().getLocation().getLatitude();
                    double restaurantLongitude = dish.getRestaurant().getLocation().getLongitude();
                    double distance = calculateDistanceService.calculateDistance(userLatitude, userLongitude, restaurantLatitude, restaurantLongitude);
                    return (minDistance == null || distance >= minDistance) && (maxDistance == null || distance <= maxDistance);
                })
                .toList();

        log.info("Inside distance filter no of dishes : {} " , list.size());
        return list;
    }

}
