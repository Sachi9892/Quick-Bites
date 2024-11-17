package com.quick_bites.location_service;

import com.quick_bites.entity.Dish;

import java.util.List;

public interface IDishesByDistanceService {

    List<Dish> getDishesByDistance(List<Dish> dishes , Double userLatitude, Double userLongitude, Double minDistance, Double maxDistance);

}
