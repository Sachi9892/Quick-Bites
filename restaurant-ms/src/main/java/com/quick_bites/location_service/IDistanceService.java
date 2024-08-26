package com.quick_bites.location_service;

import com.quick_bites.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class IDistanceService implements  DistanceService{

    private static final int EARTH_RADIUS_KM = 6371;

    @Override
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
    public List<Dish> sortDishesByDistance(List<Dish> dishes, double userLat, double userLon) {
        return dishes.stream()
                .sorted(Comparator.comparingDouble(dish -> calculateDistance(userLat, userLon,
                        dish.getRestaurant().getLocation().getLatitude(),
                        dish.getRestaurant().getLocation().getLongitude())))
                .toList();
    }


}
