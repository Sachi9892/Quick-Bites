package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.FindAllDishesByNameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class IFindAllDishesByNameService  implements FindAllDishesByNameService {

    private final DishRepository dishRepository;

    @Override
    public Page<ResponseDishDto> allDishesByName(String dishName, Pageable pageable) {

        Page<Dish> dishes = dishRepository.findAllByDishName(dishName, pageable);

        return dishes.map(DishMapper::mapToDto);

    }
}
