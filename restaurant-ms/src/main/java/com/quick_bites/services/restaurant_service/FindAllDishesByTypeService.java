package com.quick_bites.services.restaurant_service;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FindAllDishesByTypeService {

    Page<ResponseDishDto> allDishesByType(DishType dishType , Pageable pageable);


}
