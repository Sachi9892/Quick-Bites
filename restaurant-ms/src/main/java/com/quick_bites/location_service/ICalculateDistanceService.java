package com.quick_bites.location_service;

import com.quick_bites.entity.Dish;

import java.util.List;

public interface IDistanceService {

    double calculateDistance(double lat1, double lon1, double lat2, double lon2);

    List<Dish> sortDishesByDistance(List<Dish> dishes, double userLat, double userLon);

}
