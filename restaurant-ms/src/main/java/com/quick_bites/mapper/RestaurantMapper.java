package com.quick_bites.mapper;


import com.quick_bites.dto.restaurant_dto.ResponseRestDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;

import java.util.Optional;

public class RestaurantMapper {

    private RestaurantMapper() {

    }

    public static ResponseRestDto mapToDto(Optional<Restaurant> restaurant) {

        double averageRating = restaurant.get().getRestReviews().stream()
                .mapToDouble(RestaurantReview::getRating)
                .average()
                .orElse(3.0);

        return new ResponseRestDto(
                restaurant.get().getRestaurantName() ,
                restaurant.get().getMobileNumber() ,
                averageRating
        );

    }

}




