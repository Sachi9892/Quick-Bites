package com.quick_bites.location_service.impl;

import com.quick_bites.entity.Dish;
import com.quick_bites.location_service.ICalculateDistanceService;
import com.quick_bites.location_service.IDishesByDistanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DishesByDistanceServiceImpl implements IDishesByDistanceService {

    private final ICalculateDistanceService calculateDistanceService;

    @Cacheable(value = "dishes_by_distance", key = "#userLatitude + '_' + #userLongitude + '_' + #minDistance + '_' + #maxDistance")
    @Override
    public List<Dish> getDishesByDistance(List<Dish> dishes ,Double userLatitude, Double userLongitude, Double minDistance, Double maxDistance) {

        // Filter dishes by distance
        return dishes.stream()
                .filter(dish -> {
                    double restaurantLatitude = dish.getRestaurant().getLocation().getLatitude();
                    double restaurantLongitude = dish.getRestaurant().getLocation().getLongitude();
                    double distance = calculateDistanceService.calculateDistance(userLatitude, userLongitude, restaurantLatitude, restaurantLongitude);
                    log.info("Inside dishes by distance service with distance {} " , distance);
                    // Apply min and max distance filters
                    return (minDistance == null || distance >= minDistance) && (maxDistance == null || distance <= maxDistance);
                })
                .toList();

    }

}
