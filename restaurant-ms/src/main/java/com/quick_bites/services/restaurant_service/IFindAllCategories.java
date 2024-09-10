package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;

import java.util.List;
import java.util.Set;

public interface IFindAllCategories {

    Set<ResponseCategoryDto> allCategories(String restName);

    interface FindAllDishesByTypeService {

        List<ResponseDishDto> getAllDishes(String type);

    }
}
