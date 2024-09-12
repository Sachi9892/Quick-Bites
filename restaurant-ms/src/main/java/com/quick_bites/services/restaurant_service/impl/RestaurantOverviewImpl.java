package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllCategories;
import com.quick_bites.services.restaurant_service.IFindAllDishesByRestName;
import com.quick_bites.services.restaurant_service.IFindAllReviewByRest;
import com.quick_bites.services.restaurant_service.IRestaurantOverview;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RestaurantOverviewImpl implements IRestaurantOverview {

    private final RestaurantRepository restaurantRepository;
    private final IFindAllCategories categories;
    private final IFindAllDishesByRestName dishes;
    private final IFindAllReviewByRest reviews;


    @Override
    public RestaurantOverViewDto getOverView(String name, Pageable pageable) {

        Restaurant rest = restaurantRepository.findByRestaurantName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found : " + name));


        String restName = rest.getRestaurantName();
        String mob = rest.getMobileNumber();

        //First extract categories
        Set<ResponseCategoryDto> categoryDtos = categories.allCategories(restName);


        //Then dishes
        Page<ResponseDishDto> dishesDtos = dishes.findAllDishesByRestaurantName(restName , pageable);


        //Finally, reviews
        Page<ResponseReviewDto> reviewsDtos = reviews.findAllReview(restName , pageable);

        Integer totalReviews = reviewsDtos.stream().toList().size();

        return new RestaurantOverViewDto(
                restName ,
                mob ,
                dishesDtos ,
                categoryDtos ,
                reviewsDtos ,
                totalReviews
        );


    }

}
