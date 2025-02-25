package com.quick_bites.mapper;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishReview;
import com.quick_bites.entity.DishType;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DishMapper {

    private DishMapper() {
    }

    public static ResponseDishDto mapToDto(Dish dish) {

        // Log the dish name to track the object being processed
        log.info("Mapping Dish: {} " , dish.getDishName());

        // Get the average rating
        Double averageRating = dish.getDishReviews().stream()
                .mapToDouble(DishReview::getRating)
                .average()
                .orElse(3.0);
        log.info("Average Rating for  {}  , is : {} " ,  dish.getDishName() ,  averageRating);

        // Get total number of reviews
        int totalReviews = dish.getDishReviews().size();
        log.info("Total Reviews for  {} is : {}" , dish.getDishName() , totalReviews);

        // Get restaurant name and ID
        String restaurantName = dish.getRestaurant().getRestaurantName();
        Long restaurantId = dish.getRestaurant().getRestId();
        log.info("Restaurant:   {} ,   id : {}" , restaurantName ,  restaurantId);

        // Get category name
        String categoryName = dish.getCategory().getCategoryName();
        log.info("Category: {}  " , categoryName);

        // Get other dish details
        String description = dish.getDescription();
        Double price = dish.getPrice();
        DishType dishType = dish.getDishType();
        String dishPic = dish.getDishPic();

        // Create and return the DTO
        return new ResponseDishDto(
                dish.getDishId(),
                dish.getDishName(),
                restaurantName,
                categoryName,
                description,
                price,
                dishType,
                dishPic,
                restaurantId,
                averageRating,
                totalReviews
        );
    }

}
