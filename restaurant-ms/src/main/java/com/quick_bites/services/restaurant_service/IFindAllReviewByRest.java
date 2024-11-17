package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.review_dto.ResponseReviewDto;

import java.util.List;


public interface IFindAllReviewByRest {

    List<ResponseReviewDto> findAllReview(String name);

}
