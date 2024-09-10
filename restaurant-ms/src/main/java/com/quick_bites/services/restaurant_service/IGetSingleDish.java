package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.dish_dto.SingleDishResponseDto;

public interface IGetSingleDish {

    SingleDishResponseDto getDish(Long id);

}
