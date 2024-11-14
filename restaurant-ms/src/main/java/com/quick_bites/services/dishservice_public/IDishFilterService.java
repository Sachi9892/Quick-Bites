package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;

import java.util.List;

public interface IDishSortingAndFilterService {

    List<ResponseDishDto> getFilteredAndSortedDishes(

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
            boolean ascending);

}
