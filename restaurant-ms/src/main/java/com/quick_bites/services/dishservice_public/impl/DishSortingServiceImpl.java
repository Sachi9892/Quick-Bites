package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.location_service.IDishesByDistanceService;
import com.quick_bites.services.dishservice_public.IDishSortingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class DishSortingServiceImpl implements IDishSortingService {

    private final IDishesByDistanceService dishesByDistanceService;

    @Override
    public List<Dish> sortDishes(List<Dish> dishes, String sortBy, boolean ascending, Double userLatitude, Double userLongitude , Double minDistance , Double maxDistance) {

        List<Dish> sortedDishes = new ArrayList<>(dishes); // Create a new list to avoid modifying the original one

        switch (sortBy) {
            case "price":
                sortedDishes.sort((d1, d2) -> ascending
                        ? Double.compare(d1.getPrice(), d2.getPrice())
                        : Double.compare(d2.getPrice(), d1.getPrice()));
                log.info("Sort By Price Worked");
                break;
            case "rating":
                sortedDishes.sort((d1, d2) -> {
                    double avgRating1 = d1.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                    double avgRating2 = d2.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                    log.info("Sort By Rating Worked");
                    return ascending ? Double.compare(avgRating1, avgRating2) : Double.compare(avgRating2, avgRating1);

                });
                break;
            case "distance":
                if (userLatitude != null && userLongitude != null) {
                    log.info("Sort By Distance Worked");
                    sortedDishes = dishesByDistanceService.getDishesByDistance(sortedDishes , userLatitude, userLongitude, minDistance, maxDistance);
                }
                break;
            default:
                break;
        }
        return sortedDishes;
    }

}
