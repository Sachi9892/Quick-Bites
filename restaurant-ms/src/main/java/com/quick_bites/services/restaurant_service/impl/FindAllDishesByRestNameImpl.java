package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllDishesByRestName;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FindAllDishesByRestNameImpl implements IFindAllDishesByRestName {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Cacheable(value = "dishes_by_restName", key = "{#name}")
    public List<ResponseDishDto> findAllDishesByRestaurantName(String name) {

        Restaurant restaurant = restaurantRepository.findByRestaurantName(name).orElseThrow(() ->
                new RestaurantNotFoundException("No restaurant found with name:" + name));

        List<Dish> dishes = restaurantRepository.findAllDishesByRestaurantName(restaurant.getRestaurantName());

        return  dishes.stream().map(DishMapper::mapToDto).toList();

    }

}
