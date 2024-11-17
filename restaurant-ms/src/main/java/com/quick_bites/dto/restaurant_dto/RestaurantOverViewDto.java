package com.quick_bites.dto.restaurant_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantOverViewDto implements Serializable {

    private String restaurantName;
    private String mobileNumber;

    private List<ResponseDishDto> dishes;
    private List<ResponseCategoryDto> categories;
    private List<ResponseReviewDto> reviews;
    private Integer totalReviews;

}
