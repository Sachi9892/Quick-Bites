package com.quick_bites.service.user_profile.impl;


import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.entity.UserRestaurantReview;
import com.quick_bites.mapper.RestaurantReviewMapper;
import com.quick_bites.repository.RestaurantReviewRepository;
import com.quick_bites.service.user_profile.IFetchRestaurantReviews;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FetchRestaurantReviewsImpl implements IFetchRestaurantReviews {

    private final RestaurantReviewRepository restReviewRepository;

    @Override
    @Cacheable(value = "rest_reviews" , key = "{#userId}")
    public List<ResponseReviewDto> fetchRestReviewByUserId(Long userId) {

        List<UserRestaurantReview> reviews = restReviewRepository.findAllByUser_UserId(userId);

        return reviews.stream()
                .map(RestaurantReviewMapper::userRestaurantReviewToResponseReviewDto).toList();

    }
}
