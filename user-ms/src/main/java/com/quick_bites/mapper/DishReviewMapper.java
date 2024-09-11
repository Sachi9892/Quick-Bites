package com.quick_bites.mapper;

import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.entity.UserDishReview;

public class DishReviewMapper {

    private DishReviewMapper() {

    }

    public static ResponseReviewDto userDishReviewToResponseReviewDto(UserDishReview review) {
        return new ResponseReviewDto(
                review.getDishId(),
                review.getRating(),
                review.getComment(),
                review.getReviewTime()
        );
    }
}
