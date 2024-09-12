package com.quick_bites.mapper;


import com.quick_bites.dto.restaurant_dto.ResponseRestDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;

import java.util.Optional;

public class RestaurantMapper {

    private RestaurantMapper() {

    }

    public static ResponseRestDto mapToDto(Restaurant restaurant) {

        Double averageRating = restaurant.getRestReviews().stream()
                .mapToDouble(RestaurantReview::getRating)
                .average()
                .orElse(3.0);

        Integer totalRating = restaurant.getRestReviews().size();


        return new ResponseRestDto(
                restaurant.getRestaurantName() ,
                restaurant.getMobileNumber() ,
                averageRating ,
                totalRating
        );

    }

}




