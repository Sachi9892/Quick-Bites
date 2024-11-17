package com.quick_bites.location_service.impl;

import com.quick_bites.location_service.ICalculateDistanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CalculateDistanceServiceImpl implements ICalculateDistanceService {

    private static final int EARTH_RADIUS_KM = 6371;

    @Override
    public Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        log.info("Distance is {} " , EARTH_RADIUS_KM * c);
        return EARTH_RADIUS_KM * c;

    }



}
