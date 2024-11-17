package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.IFindAllCategories;
import com.quick_bites.services.restaurant_service.IFindAllDishesByRestName;
import com.quick_bites.services.restaurant_service.IFindAllReviewByRest;
import com.quick_bites.services.restaurant_service.IRestaurantOverview;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantOverviewImpl implements IRestaurantOverview {

    private final RestaurantRepository restaurantRepository;
    private final IFindAllCategories categories;
    private final IFindAllDishesByRestName dishes;
    private final IFindAllReviewByRest reviews;


    @Override
    public RestaurantOverViewDto getOverView(String name) {

        Restaurant rest = restaurantRepository.findByRestaurantName(name)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found : " + name));


        String restName = rest.getRestaurantName();
        String mob = rest.getMobileNumber();

        // Convert categories to Set explicitly
        List<ResponseCategoryDto> categoryDtos = new ArrayList<>(categories.allCategories(restName));

        // Ensure dishes are in the form of List
        List<ResponseDishDto> dishesDtos = new ArrayList<>(dishes.findAllDishesByRestaurantName(restName));

        // Get reviews as List
        List<ResponseReviewDto> reviewsDtos = new ArrayList<>(reviews.findAllReview(restName));

        Integer totalReviews = reviewsDtos.stream().toList().size();



        RestaurantOverViewDto restaurantOverViewDto = new RestaurantOverViewDto(
                restName,
                mob,
                dishesDtos,
                categoryDtos,
                reviewsDtos,
                totalReviews
        );

        log.info("Returning Restaurant Overview: {}", restaurantOverViewDto);

        return restaurantOverViewDto;


    }

}
