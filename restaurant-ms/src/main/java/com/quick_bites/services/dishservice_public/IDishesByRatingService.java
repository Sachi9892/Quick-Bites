package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;


import java.util.List;

public interface DishesByRatingService {

    List<ResponseDishDto> dishesByReview(Double rating );
}