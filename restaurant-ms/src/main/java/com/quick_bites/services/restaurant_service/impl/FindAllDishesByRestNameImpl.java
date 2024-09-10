package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllDishesByRestName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class FindAllDishesByRestNameImpl implements IFindAllDishesByRestName {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<ResponseDishDto> findAllDishesByRestaurantName(String name, Pageable pageable) {

        Restaurant restaurant = restaurantRepository.findByRestaurantName(name).orElseThrow(() ->
                new ResourceNotFoundException("No restaurant found with name:" + name));

        Page<Dish> dishes = restaurantRepository.findAllDishesByRestaurantName(restaurant.getRestaurantName(), pageable);

        return  dishes.map(DishMapper::mapToDto);

    }

}
