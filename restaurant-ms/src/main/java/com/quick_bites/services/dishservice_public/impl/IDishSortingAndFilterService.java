package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.DishType;
import com.quick_bites.location_service.DistanceService;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dishservice_public.DishSortingAndFilterService;
import com.quick_bites.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class IDishSortingAndFilterService implements DishSortingAndFilterService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DistanceService distanceService;

    @Override
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
            User user,
            String sortBy,
            boolean ascending) {

        List<Dish> dishes;

       dishes = restaurantRepository.findAllDishesByDishName(query , Pageable.unpaged()).getContent();

       log.info("dishes by dish name : {} " , dishes);

       if(dishes.isEmpty()) {
           dishes = restaurantRepository.findAllDishesByRestaurantName(query , Pageable.unpaged()).getContent();
           log.info("dishes by restaurant name : {} " , dishes);
       }

        // Apply price filtering
        if (minPrice != null && maxPrice != null) {
            dishes = dishRepository.findAllByPriceBetween(minPrice , maxPrice);
            log.info("dishes by price : {} " , dishes);
        }

        // Apply rating filtering
        if (minRating != null) {
            dishes = dishRepository.findAllByDishReviewsRatingGreaterThanEqual(minRating).stream().toList();
            log.info("dishes by rating : {} " , dishes);
        }

        if(dishType != null) {
            dishes = dishRepository.findAllByDishType(dishType).stream().toList();
            log.info("dishes by dish-type : {} " , dishes);
        }


        // Handle distance filtering and sorting
        if (userLatitude != null && userLongitude != null && "distance".equals(sortBy)) {
            // Use DistanceService for sorting by distance
            dishes = distanceService.sortDishesByDistance(dishes, userLatitude, userLongitude);

            // Apply distance filtering
            if (minDistance != null || maxDistance != null) {
                dishes = dishes.stream()
                        .filter(dish -> {
                            double distance = distanceService.calculateDistance(
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

}