package com.quick_bites.service.user_profile.impl;

import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import com.quick_bites.entity.UserDishReview;
import com.quick_bites.mapper.DishReviewMapper;
import com.quick_bites.repository.DishReviewRepository;
import com.quick_bites.service.user_profile.IFetchDishReviews;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FetchDishReviewsImpl implements IFetchDishReviews {

    private final DishReviewRepository dishReviewRepository;

    @Override
    public List<ResponseReviewDto> fetchDishReviewByUserId(Long userId) {

        List<UserDishReview> reviews = dishReviewRepository.findAllByUser_UserId(userId);

        return reviews.stream().map(
                DishReviewMapper::userDishReviewToResponseReviewDto).toList();

    }

}
