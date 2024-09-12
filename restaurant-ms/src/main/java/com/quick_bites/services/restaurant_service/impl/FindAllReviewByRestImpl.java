package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllReviewByRest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class FindAllReviewByRestImpl implements IFindAllReviewByRest {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<ResponseReviewDto> findAllReview(String name , Pageable pageable) {

        Restaurant rest = restaurantRepository.findByRestaurantName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("No Restaurant Found : " + name));

        Page<RestaurantReview> reviews = restaurantRepository.findAllReviewsByRestaurantName(rest.getRestaurantName() , pageable);

        return reviews.map(
                review -> new ResponseReviewDto(
                        rest.getRestId(),
                        review.getRating(),
                        review.getComment()
                )
        );


    }
}
