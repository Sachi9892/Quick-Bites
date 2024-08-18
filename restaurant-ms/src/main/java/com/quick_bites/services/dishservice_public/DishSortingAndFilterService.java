package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;
import com.quick_bites.entity.User;

import java.util.List;

public interface DishSortingAndFilterService {

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
            boolean ascending);

}
