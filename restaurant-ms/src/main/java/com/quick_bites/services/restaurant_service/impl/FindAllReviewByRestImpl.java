package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllReviewByRest;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class    FindAllReviewByRestImpl implements IFindAllReviewByRest {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Cacheable(value = "rest_reviews", key = "#name")
    public List<ResponseReviewDto> findAllReview(String name) {

        Restaurant rest = restaurantRepository.findByRestaurantName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("No Restaurant Found : " + name));

        List<RestaurantReview> reviews = restaurantRepository.findAllReviewsByRestaurantName(rest.getRestaurantName());

        return reviews.stream().map(
                review -> new ResponseReviewDto(
                        rest.getRestId(),
                        review.getRating(),
                        review.getComment()
                )
        ).toList();


    }
}
