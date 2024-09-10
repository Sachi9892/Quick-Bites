package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.restaurant_service.IFindDishPriceAndRestIdByDishId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class FindDishPriceByIdImpl implements IFindDishPriceAndRestIdByDishId {

    private final DishRepository dishRepository;

    @Override
    public Double getPrice(Long dishId) {

        return dishRepository.findPriceByDishId(dishId);

    }
}
