package com.quick_bites.services.review_service;

import com.quick_bites.dto.review_dto.GiveReviewDto;

public interface GiveReviewToDish {

    void giveReview(GiveReviewDto reviewDto ,Long dishId);

}
