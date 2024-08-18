package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.DishesByRatingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class IDishesByRatingService implements DishesByRatingService {

    private final DishRepository dishRepository;

    @Override
    public List<ResponseDishDto> dishesByReview(double rating, Pageable pageable) {

        List<Dish> dishes = dishRepository.findAllByDishReviewsRatingGreaterThanEqual(rating, pageable);

         return dishes.stream().map(DishMapper::mapToDto).toList();

    }

}
