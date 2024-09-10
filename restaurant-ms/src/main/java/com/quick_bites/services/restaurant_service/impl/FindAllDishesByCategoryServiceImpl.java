package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllDishesByCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindAllDishesByCategoryServiceImpl implements IFindAllDishesByCategoryService {

    private final RestaurantRepository restaurantRepository;


    @Override
    public Page<ResponseDishDto> allDishesByCategory(String name , Pageable pageable) {

        Page<Dish> dishes = restaurantRepository.findAllDishesByCategoryName(name , pageable);

        return  dishes.map(DishMapper::mapToDto);

    }


}
