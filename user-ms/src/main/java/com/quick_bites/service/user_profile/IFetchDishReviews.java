package com.quick_bites.service.user_profile;


import com.quick_bites.dto.reviewdto.ResponseReviewDto;

import java.util.List;

public interface IFetchDishReviews {

    List<ResponseReviewDto> fetchDishReviewByUserId(Long userId);

}
