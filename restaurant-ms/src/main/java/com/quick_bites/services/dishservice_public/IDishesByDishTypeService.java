package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;

import java.util.List;

public interface DishesByDishTypeService {

    List<ResponseDishDto> dishesByDishType(DishType dishType);

}
