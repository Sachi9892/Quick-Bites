package com.quick_bites.services.restaurant_service.impl;


import com.quick_bites.dto.dish_dto.SingleDishResponseDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.restaurant_service.IGetSingleDish;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

@Service
@AllArgsConstructor
public class GetSingleDishImpl implements IGetSingleDish {

    private final DishRepository dishRepository;

    @Override
    public SingleDishResponseDto getDish(Long id) {

        Dish dish = dishRepository.findById(id).orElseThrow(() -> new NoResourceFoundException("No Dish With Id" + id));

        return new SingleDishResponseDto(

                dish.getDishId(),
                dish.getRestaurant().getRestId(),
                dish.getDishName(),
                dish.getPrice(),
                dish.getDishType(),
                dish.getDishPic()
                
        );
    }
}
