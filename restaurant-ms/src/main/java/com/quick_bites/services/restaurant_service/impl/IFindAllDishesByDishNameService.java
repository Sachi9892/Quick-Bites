package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.FindAllDishesByDishNameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IFindAllDishesByDishNameService implements FindAllDishesByDishNameService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<ResponseDishDto> allDishesByDishName(String name, Pageable pageable) {

        Page<Dish> dishes = restaurantRepository.findAllDishesByDishName(name, pageable);
        return dishes.map(DishMapper::mapToDto);

    }
}
