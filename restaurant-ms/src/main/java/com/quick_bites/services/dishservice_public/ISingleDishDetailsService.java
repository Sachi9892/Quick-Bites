package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.SingleDishResponseDto;

public interface ISingleDishDetailsService {

    SingleDishResponseDto dishDetails(Long dishId);

}
