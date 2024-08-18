package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Category;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.restaurant_service.FindAllCategories;
import com.quick_bites.services.restaurant_service.FindAllDishesByRestName;
import com.quick_bites.services.restaurant_service.FindAllReviewByRest;
import com.quick_bites.services.restaurant_service.RestaurantOverview;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class IRestaurantOverview implements RestaurantOverview {

    private final RestaurantRepository restaurantRepository;
    private final FindAllCategories categories;
    private final FindAllDishesByRestName dishes;
    private final FindAllReviewByRest reviews;


    @Override
    public RestaurantOverViewDto getOverView(String name, Pageable pageable) {

        Optional<Restaurant> rest = restaurantRepository.findByRestaurantName(name);

        if(rest.isEmpty()) {
            throw new ResourceNotFoundException("No Restaurant found with name" + name);
        }

        String restName = rest.get().getRestaurantName();
        String mob = rest.get().getMobileNumber();

        //First extract categories
        Set<ResponseCategoryDto> categoryDtos = categories.allCategories(restName);


        //Then dishes
        Page<ResponseDishDto> dishesDtos = dishes.findAllDishesByRestaurantName(restName , pageable);


        //Finally reviews
        Page<ResponseReviewDto> reviewsDtos = reviews.findAllReview(restName , pageable);


        return new RestaurantOverViewDto(
                restName ,
                mob ,
                dishesDtos ,
                categoryDtos ,
                reviewsDtos
        );


    }

}
