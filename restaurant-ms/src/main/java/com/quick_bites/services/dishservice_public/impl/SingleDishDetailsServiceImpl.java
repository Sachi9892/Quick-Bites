package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.SingleDishResponseDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.exception.DishNotFoundException;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.ISingleDishDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SingleDishDetailsServiceImpl implements ISingleDishDetailsService {

    private final DishRepository dishRepository;

    @Override
    public SingleDishResponseDto dishDetails(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFoundException("No dish with id " + dishId));

        return new SingleDishResponseDto(
                dish.getDishId() ,
                dish.getRestaurant().getRestId(),
                dish.getDishName(),
                dish.getPrice(),
                dish.getDishType(),
                dish.getDishPic()
        );
    }
}
