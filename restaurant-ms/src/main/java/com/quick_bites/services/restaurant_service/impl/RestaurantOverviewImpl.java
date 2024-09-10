package com.quick_bites.services.restaurant_service.impl;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceNotFoundException;
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
