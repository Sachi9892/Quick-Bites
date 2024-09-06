package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.restaurant_dto.RestIdAndDishPrice;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.restaurant_service.FindDishPriceAndRestIdByDishId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IFindDishPriceById  implements FindDishPriceAndRestIdByDishId {

    private final DishRepository dishRepository;

    @Override
    public Double getPrice(Long dishId) {

        return dishRepository.findPriceByDishId(dishId);

    }
}
