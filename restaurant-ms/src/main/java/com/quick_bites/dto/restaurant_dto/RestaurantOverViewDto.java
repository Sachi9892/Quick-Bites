package com.quick_bites.dto.restaurant_dto;


import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOverViewDto {

    private String restaurantName;
    private String mobileNumber;


    private Page<ResponseDishDto> dishes;
    private Set<ResponseCategoryDto> categories;
    private Page<ResponseReviewDto> reviews;
    private Integer totalReviews;

}
