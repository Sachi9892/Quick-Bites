package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllDishesByDishNameService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FindAllDishesByDishNameServiceImpl implements IFindAllDishesByDishNameService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Cacheable(value = "dishes_by_dishName" , key = "#name")
    public List<ResponseDishDto> allDishesByDishName(String name) {

        List<Dish> dishes = restaurantRepository.findAllDishesByDishName(name);
        return dishes.stream().map(DishMapper::mapToDto).toList();

    }
}
