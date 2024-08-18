package com.quick_bites.services.restaurant_service;

import com.quick_bites.dto.review_dto.ResponseReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface FindAllReviewByRest {

    Page<ResponseReviewDto> findAllReview(String name , Pageable pageable);

}
