package com.quick_bites.services.dishservice_public.impl;


import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.location_service.ICalculateDistanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class DishSorter {

    private final ICalculateDistanceService calculateDistanceService;

    /**
     * Sorts the given list of dishes based on the sorting criteria.
     */

    public List<Dish> sortDishes(List<Dish> dishes, String sortBy, boolean ascending, Double userLatitude, Double userLongitude) {
        if (sortBy == null) return dishes; // No sorting applied

        Comparator<Dish> comparator = getComparator(sortBy, ascending, userLatitude, userLongitude);
        return dishes.stream().sorted(comparator).toList();
    }

    private Comparator<Dish> getComparator(String sortBy, boolean ascending, Double userLatitude, Double userLongitude) {
        return switch (sortBy) {
            case "price" -> ascending
                    ? Comparator.comparingDouble(Dish::getPrice)
                    : Comparator.comparingDouble(Dish::getPrice).reversed();
            case "rating" -> (d1, d2) -> {
                double avgRating1 = d1.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(3.0);
                double avgRating2 = d2.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(3.0);
                return ascending ? Double.compare(avgRating1, avgRating2) : Double.compare(avgRating2, avgRating1);
            };
            case "distance" -> userLatitude != null && userLongitude != null
                    ? (dish, dish2) -> {
                double restLat = dish.getRestaurant().getLocation().getLatitude();
                double restLong = dish.getRestaurant().getLocation().getLongitude();
                double distance1 = calculateDistanceService.calculateDistance(userLatitude, userLongitude, restLat, restLong);
                double distance2 = calculateDistanceService.calculateDistance(userLatitude, userLongitude, restLat, restLong);
                return ascending ? Double.compare(distance1, distance2) : Double.compare(distance2, distance1);
            }
                    : Comparator.comparingDouble(Dish::getPrice); // Default fallback
            default -> Comparator.comparingDouble(Dish::getPrice); // Default sorting by price
        };
    }

}
