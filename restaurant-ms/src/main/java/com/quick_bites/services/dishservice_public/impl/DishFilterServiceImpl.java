package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.DishType;
import com.quick_bites.location_service.ICalculateDistanceService;
import com.quick_bites.location_service.IDishesByDistanceService;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dishservice_public.IDishFilterService;
import com.quick_bites.services.dishservice_public.IDishSortingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DishFilterServiceImpl implements IDishFilterService {

    private final RestaurantRepository restaurantRepository;
    private final IDishSortingService dishSortingService;
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
            log.info("Fetching dishes from restaurant repo");
        }


        log.info("Inside rest-ms search and sort service");

        // Apply all filters
        dishes = dishes.stream()
                .filter(dish -> {
                    boolean matches = true;

                    // Filter by price range
                    if (minPrice != null && maxPrice != null) {
                        matches = dish.getPrice() >= minPrice && dish.getPrice() <= maxPrice;
                        log.info("Inside MinPrice And MaxPrice filter");
                    }

                    // Filter by rating
                    if (minRating != null) {
                        double avgRating = dish.getDishReviews().stream().mapToDouble(DishReview::getRating).average().orElse(0.0);
                        matches = matches && avgRating >= minRating;
                        log.info("Inside MinRating filter");
                    }

                    // Filter by dish type
                    if (dishType != null) {
                        matches = matches && dish.getDishType().equals(dishType);
                        log.info("Inside DishType filter");
                    }

                    // Filter by distance
                    if (userLatitude != null && userLongitude != null && (minDistance != null || maxDistance != null)) {

                        double distance = distanceService.calculateDistance(
                                userLatitude, userLongitude,
                                dish.getRestaurant().getLocation().getLatitude(),
                                dish.getRestaurant().getLocation().getLongitude());
                        matches = matches && (minDistance == null || distance >= minDistance) &&
                                (maxDistance == null || distance <= maxDistance);
                        log.info("Inside filter by distance  :  {} " , distance);
                    }

                    return matches;
                })
                .toList();

        // Now sort the filtered dishes
        if (sortBy != null) {
            log.info("Entering sorting service ");
            dishes = dishSortingService.sortDishes(dishes, sortBy, ascending, userLatitude, userLongitude , minDistance , maxDistance);
        }

        log.info("Result returned from FILTER SERVICE ONLY");

        return dishes.stream().map(DishMapper::mapToDto).toList();
    }


}