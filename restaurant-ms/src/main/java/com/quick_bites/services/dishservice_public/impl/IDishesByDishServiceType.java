package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.DishesByDishTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class IDishesByDishServiceType implements DishesByDishTypeService {

    private final DishRepository dishRepository;

    @Override
    public List<ResponseDishDto> dishesByDishType(DishType dishType) {

        List<Dish> dishes = dishRepository.findAllByDishType(dishType);
        return dishes.stream().map(DishMapper::mapToDto).toList();

    }
}
