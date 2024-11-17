package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.dish_dto.ResponseDishDto;

import java.util.List;


public interface IFindAllDishesByCategoryService {

    List<ResponseDishDto> allDishesByCategory(String name);

}
