package com.quick_bites.services.dishservice_public;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindAllDishesByPrice {

    List<ResponseDishDto> dishesByPrice(double minPrice, double maxPrice);

}
