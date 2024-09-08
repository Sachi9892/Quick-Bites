package com.quick_bites.services.restaurant_service;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllDishesByRestName {

    Page<ResponseDishDto> findAllDishesByRestaurantName(String name , Pageable pageable);

}
