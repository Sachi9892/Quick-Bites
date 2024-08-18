package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.restaurant_service.FindAllDishesByTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Log4j2
public class IFindAllDishesByTypeService implements FindAllDishesByTypeService {

    private final DishRepository dishRepo;

    @Override
    public Page<ResponseDishDto> allDishesByType(DishType dishType , Pageable pageable) {

        Page<Dish> dishes = dishRepo.findAllByDishType(dishType, pageable);

        return  dishes.map(DishMapper::mapToDto);

    }

}
