package com.quick_bites.mapper;

import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.entity.UserRestaurantReview;

public class RestaurantReviewMapper {


    private RestaurantReviewMapper() {

    }


    public static ResponseReviewDto userRestaurantReviewToResponseReviewDto(UserRestaurantReview review) {
        return new ResponseReviewDto(
                review.getRestaurantId(),
                review.getRating(),
                review.getComment()
        );
    }
}
