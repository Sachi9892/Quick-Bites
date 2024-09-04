package com.quick_bites.services.dishservice_public.impl;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.FindAllDishesByPrice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IFindAllDishesByPrice implements FindAllDishesByPrice {

    private final DishRepository dishRepository;

    @Override
    public List<ResponseDishDto> dishesByPrice(Double minPrice, Double maxPrice) {
        List<Dish> dishes = dishRepository.findAllByPriceBetween(minPrice, maxPrice);
        return dishes.stream().map(DishMapper::mapToDto).toList();
    }
}
