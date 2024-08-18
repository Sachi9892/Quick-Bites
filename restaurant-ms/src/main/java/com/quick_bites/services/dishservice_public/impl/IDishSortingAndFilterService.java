package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.location_service.GeoCodingService;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dishservice_public.DishSortingAndFilterService;
import com.quick_bites.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class IDishSortingAndFilterService implements DishSortingAndFilterService {

    private final DishRepository dishRepository;
    private final GeoCodingService geoCodingService;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<ResponseDishDto> getFilteredAndSortedDishes(
            String category,
            String name,
            String restaurant,
            Double minPrice,
            Double maxPrice,
            Double minRating,
            Double minDistance,
            Double maxDistance,
            Double userLatitude,
            Double userLongitude,
            DishType dishType,
            User user,
            String sortBy,
            boolean ascending) {

        List<Dish> dishes;

        // Fetch dishes based on dishType first
        if (dishType != null) {
            dishes = dishRepository.findAllByDishType(dishType, Pageable.unpaged()).getContent();
        } else {
            dishes = dishRepository.findAll(Pageable.unpaged()).getContent();
        }

        // Further filter by name, category, or restaurant if provided
        if (name != null) {
            dishes = dishes.stream()
                    .filter(dish -> dish.getDishName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        } else if (category != null) {
            dishes = dishes.stream()
                    .filter(dish -> dish.getCategory().getCategoryName().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        } else if (restaurant != null) {
            dishes = dishes.stream()
                    .filter(dish -> dish.getRestaurant().getRestaurantName().equalsIgnoreCase(restaurant))
                    .collect(Collectors.toList());
        }

        // Apply price filtering
        if (minPrice != null && maxPrice != null) {
            dishes = dishes.stream()
                    .filter(dish -> dish.getPrice() >= minPrice && dish.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        // Apply rating filtering
        if (minRating != null) {
            dishes = dishes.stream()
                    .filter(dish -> dish.getDishReviews().stream()
                            .mapToDouble(DishReview::getRating)
                            .average().orElse(0.0) >= minRating)
                    .collect(Collectors.toList());
        }

        // Handle distance filtering and sorting
        if (userLatitude != null && userLongitude != null && "distance".equals(sortBy)) {
            // Use your custom sortDishesByDistance method
            dishes = sortDishesByDistance(dishes, userLatitude, userLongitude);

            // Apply distance filtering
            if (minDistance != null || maxDistance != null) {
                dishes = dishes.stream()
                        .filter(dish -> {
                            double distance = calculateDistance(
                                    userLatitude, userLongitude,
                                    dish.getRestaurant().getLocation().getLatitude(),
                                    dish.getRestaurant().getLocation().getLongitude());
                            return (minDistance == null || distance >= minDistance) &&
                                    (maxDistance == null || distance <= maxDistance);
                        })
                        .collect(Collectors.toList());
            }
        } else if (sortBy != null) {
            // Sort by price or rating if not sorting by distance
            if ("price".equals(sortBy)) {
                dishes.sort((d1, d2) -> ascending
                        ? Double.compare(d1.getPrice(), d2.getPrice())
                        : Double.compare(d2.getPrice(), d1.getPrice()));
            } else if ("rating".equals(sortBy)) {
                dishes.sort((d1, d2) -> {
                    double avgRating1 = d1.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                    double avgRating2 = d2.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                    return ascending ? Double.compare(avgRating1, avgRating2) : Double.compare(avgRating2, avgRating1);
                });
            }
        }

        return dishes.stream().map(DishMapper::mapToDto).toList();
    }

    private List<Dish> sortDishesByDistance(List<Dish> dishes, double userLat, double userLon) {
        return dishes.stream()
                .sorted(Comparator.comparingDouble(dish -> calculateDistance(userLat, userLon,
                        dish.getRestaurant().getLocation().getLatitude(),
                        dish.getRestaurant().getLocation().getLongitude())))
                .toList();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
}