package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.DishType;
import com.quick_bites.location_service.ICalculateDistanceService;
import com.quick_bites.location_service.IDishesByDistanceService;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dishservice_public.IDishSortingAndFilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DishSortingAndFilterServiceImpl implements IDishSortingAndFilterService {

    private final RestaurantRepository restaurantRepository;
    private final IDishesByDistanceService dishesByDistanceService;
    private final ICalculateDistanceService  distanceService;


    @Override
    @Cacheable(value = "dishes", key = "{#query, #minPrice, #maxPrice, #minRating, #minDistance, #maxDistance, #userLatitude, #userLongitude, #dishType, #sortBy, #ascending}")
    public List<ResponseDishDto> getFilteredAndSortedDishes(

            String query,
            Double minPrice,
            Double maxPrice,
            Double minRating,
            Double minDistance,
            Double maxDistance,
            Double userLatitude,
            Double userLongitude,
            DishType dishType,
            String sortBy,
            boolean ascending) {



        List<Dish> dishes = restaurantRepository.findAllDishesByDishName(query);

        if (dishes.isEmpty()) {
            dishes = restaurantRepository.findAllDishesByRestaurantName(query);
        }


        log.info("Inside rest-ms search and sort service");

        // Apply all filters
        dishes = dishes.stream()
                .filter(dish -> {
                    boolean matches = true;

                    // Filter by price range
                    if (minPrice != null && maxPrice != null) {
                        matches = dish.getPrice() >= minPrice && dish.getPrice() <= maxPrice;
                    }

                    // Filter by rating
                    if (minRating != null) {
                        double avgRating = dish.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                        matches = matches && avgRating >= minRating;
                    }

                    // Filter by dish type
                    if (dishType != null) {
                        matches = matches && dish.getDishType().equals(dishType);
                    }

                    // Filter by distance
                    if (userLatitude != null && userLongitude != null && (minDistance != null || maxDistance != null)) {
                        double distance = distanceService.calculateDistance(
                                userLatitude, userLongitude,
                                dish.getRestaurant().getLocation().getLatitude(),
                                dish.getRestaurant().getLocation().getLongitude());
                        matches = matches && (minDistance == null || distance >= minDistance) &&
                                (maxDistance == null || distance <= maxDistance);
                    }

                    return matches;
                })
                .toList();

        // Sort the filtered dishes
        if (sortBy != null) {
            switch (sortBy) {
                case "price":
                    dishes.sort((d1, d2) -> ascending
                            ? Double.compare(d1.getPrice(), d2.getPrice())
                            : Double.compare(d2.getPrice(), d1.getPrice()));
                    break;
                case "rating":
                    dishes.sort((d1, d2) -> {
                        double avgRating1 = d1.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                        double avgRating2 = d2.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                        return ascending ? Double.compare(avgRating1, avgRating2) : Double.compare(avgRating2, avgRating1);
                    });
                    break;
                case "distance":
                    if (userLatitude != null && userLongitude != null) {
                        dishes = dishesByDistanceService.getDishesByDistance(dishes ,userLatitude, userLongitude, minDistance, maxDistance);
                    }
                    break;
                default:
                    break;
            }
        }

        return dishes.stream().map(DishMapper::mapToDto).toList();
    }


}