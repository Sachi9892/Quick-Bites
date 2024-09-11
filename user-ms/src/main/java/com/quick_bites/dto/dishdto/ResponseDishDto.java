package com.quick_bites.dto.dishdto;

import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseDishDto {


    private Long dishId;

    private String dishName;

    private String category;

    private String description;

    private Double price;

    private DishType dishType;

    private String dishPic;

    private Long restId;

    private Double avgReview;

    private int totalReviews;

    private List<ResponseReviewDto> reviews;


}
