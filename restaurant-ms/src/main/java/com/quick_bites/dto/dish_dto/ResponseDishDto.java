package com.quick_bites.dto.dish_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quick_bites.dto.review_dto.ResponseReviewDto;
import com.quick_bites.entity.DishType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDishDto implements Serializable {

    private Long dishId;

    private String dishName;

    private String restName;

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
