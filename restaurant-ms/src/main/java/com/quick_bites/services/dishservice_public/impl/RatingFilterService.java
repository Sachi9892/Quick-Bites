package com.quick_bites.services.dishservice_public.impl;

import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class RatingFilterService {

    public List<Dish> filterByRating(List<Dish> dishes, Double minRating) {
        if (minRating == null) return dishes;
        List<Dish> list = dishes.stream()
                .filter(dish -> dish.getDishReviews().stream()
                        .mapToDouble(DishReview::getRating)
                        .average().orElse(0.0) >= minRating)
                .toList();

        log.info("Inside rating filter no of dishes : {} " , list.size());
        return list;
    }

}
