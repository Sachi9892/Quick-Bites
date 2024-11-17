package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.services.dishservice_public.IDishesByRatingService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class DishesByRatingServiceImpl implements IDishesByRatingService {

    private final DishRepository dishRepository;

    @Override
    @Cacheable(value = "dishes_by_rating", key = "{#rating}")
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
