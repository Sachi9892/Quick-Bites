package com.quick_bites.location_service.impl;

import com.quick_bites.entity.Dish;
import com.quick_bites.location_service.IDistanceService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class DistanceServiceImpl implements IDistanceService {

    private static final int EARTH_RADIUS_KM = 6371;

    @Override
    @Cacheable(value = "distance_calculations", key = "{#lat1 , #lon1 , #lat2 , #lon2}")
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;

    }


    @Override

    @Cacheable(value = "sorted_dishes_by_distance", key = "#userLat + '_' + #userLon")
    public List<Dish> sortDishesByDistance(List<Dish> dishes, double userLat, double userLon) {
        return dishes.stream()
                .sorted(Comparator.comparingDouble(dish -> calculateDistance(userLat, userLon,
                        dish.getRestaurant().getLocation().getLatitude(),
                        dish.getRestaurant().getLocation().getLongitude())))
                .toList();
    }


}
