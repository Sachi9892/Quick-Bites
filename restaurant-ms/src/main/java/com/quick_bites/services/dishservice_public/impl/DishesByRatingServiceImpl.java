package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.DishesByRatingService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class IDishesByRatingService implements DishesByRatingService {

    private final DishRepository dishRepository;

    @Override
    @Cacheable(value = "dishes" , key = "rating")
    public List<ResponseDishDto> dishesByReview(Double rating) {

        List<Dish> dishes = dishRepository.findAllByDishReviewsRatingGreaterThanEqual(rating);


        return dishes.stream()
                .filter(dish -> {
                    double avgRating = dish.getDishReviews().stream()
                            .mapToDouble(DishReview::getRating)
                            .average()
                            .orElse(0.0);
                    return avgRating >= rating;
                })
                .map(DishMapper::mapToDto)
                .toList();

    }

}