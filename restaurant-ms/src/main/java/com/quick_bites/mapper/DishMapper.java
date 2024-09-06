package com.quick_bites.mapper;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;


public class DishMapper {

    private DishMapper() {

    }

    public static ResponseDishDto mapToDto(Dish dish) {

        Double averageRating = dish.getDishReviews().stream()
                .mapToDouble(DishReview::getRating)
                .average()
                .orElse(3.0);


        return new ResponseDishDto(

                dish.getDishId(),
                dish.getDishName(),
                dish.getCategory().getCategoryName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getDishType(),
                dish.getDishPic(),
                dish.getRestaurant().getRestId(),
                averageRating ,
                dish.getDishReviews().stream()
                        .map(review -> new ResponseReviewDto( review.getRating(), review.getComment()))
                        .toList()
        );
    }
}
