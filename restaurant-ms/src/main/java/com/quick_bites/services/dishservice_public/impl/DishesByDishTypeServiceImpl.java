package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.IDishesByDishTypeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class DishesByDishTypeServiceImpl implements IDishesByDishTypeService {

    private final DishRepository dishRepository;

    @Override
    @Cacheable(value = "dishes_by_type", key = "{#dishType}")
    public List<ResponseDishDto> dishesByDishType(DishType dishType) {

        List<Dish> dishes = dishRepository.findAllByDishType(dishType);
        return dishes.stream().map(DishMapper::mapToDto).toList();

    }
}
