package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dishservice_public.IDishFilterService;
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

    private final PriceFilterService priceFilterService;
    private final RatingFilterService ratingFilterService;
    private final DishTypeFilterService dishTypeFilterService;
    private final DistanceFilterService distanceFilterService;
    private final DishSorter dishSortingService;

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

        // Fetch dishes by dish name or restaurant name
        List<Dish> dishes = restaurantRepository.findAllDishesByDishName(query);
        if (dishes.isEmpty()) {
            dishes = restaurantRepository.findAllDishesByRestaurantName(query);

        }

        if (minPrice != null || maxPrice != null) {
            dishes = priceFilterService.filterByPrice(dishes, minPrice, maxPrice);
            log.info("After price filter no of dishes : {} " , dishes.size());
        }

        if (minRating != null) {
            dishes = ratingFilterService.filterByRating(dishes, minRating);
            log.info("After rating filter no of dishes : {} " , dishes.size());
        }

        if (dishType != null) {
            dishes = dishTypeFilterService.filterByType(dishes, dishType);
            log.info("After type filter no of dishes : {} " , dishes.size());
        }

        if (userLatitude != null && userLongitude != null) {
            dishes = distanceFilterService.filterByDistance(dishes, userLatitude, userLongitude, minDistance, maxDistance);
            log.info("After distance filter no of dishes : {} " , dishes.size());
        }

        // Sort dishes if sorting is applied
        if (sortBy != null) {
            dishes = dishSortingService.sortDishes(dishes, sortBy, ascending, userLatitude, userLongitude);
        }

        log.info("Final no of dishes {} " , dishes.size());

        return mapToDtoList(dishes);
    }


    private List<ResponseDishDto> mapToDtoList(List<Dish> dishes) {
        return dishes.stream().map(DishMapper::mapToDto).toList();
    }


}