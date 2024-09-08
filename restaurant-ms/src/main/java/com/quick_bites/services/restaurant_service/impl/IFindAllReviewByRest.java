package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.FindAllReviewByRest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class IFindAllReviewByRest implements FindAllReviewByRest {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<ResponseReviewDto> findAllReview(String name , Pageable pageable) {

        Optional<Restaurant> rest = restaurantRepository.findByRestaurantName(name);

        if (rest.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant not found");
        }

        Page<RestaurantReview> reviews = restaurantRepository.findAllReviewsByRestaurantName(rest.get().getRestaurantName() , pageable);

        return reviews.map(
                review -> new ResponseReviewDto(
                        review.getRating(),
                        review.getComment()
                )
        );


    }
}
