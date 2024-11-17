package com.quick_bites.location_service;

import com.quick_bites.entity.Dish;

import java.util.List;

public interface ICalculateDistanceService {

    Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2);

}
