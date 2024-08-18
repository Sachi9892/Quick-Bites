package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishesByRatingService {

    List<ResponseDishDto> dishesByReview(double rating , Pageable pageable);

}
