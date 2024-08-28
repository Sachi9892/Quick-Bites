package com.quick_bites.service.managers.dish_rendering_manager.search;


import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.DishClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SearchAndSortApi {

    private final DishClient dishClient;

    public List<ResponseDishDto> searchAndSortDishes(
            String query,
            Double minPrice,
            Double maxPrice,
            Double minRating,
            Double minDistance,
            Double maxDistance,
            Double userLatitude,
            Double userLongitude,
            String dishType,
            String sortBy,
            boolean ascending) {

        List<ResponseDishDto> dishes = dishClient.getFilteredAndSortedDishes(
                query, minPrice, maxPrice, minRating, minDistance,
                maxDistance, userLatitude, userLongitude, dishType , sortBy, ascending
        );

        log.info("No of dish returned {} " , dishes.size());

        return dishes;

    }

}
