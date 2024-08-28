package com.quick_bites.dto.dishdto;

import com.quick_bites.dto.reviewdto.ResponseReviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseDishDto {

    private String dishName;

    private String category;

    private String description;

    private float price;

    private DishType dishType;

    private String dishPic;

    private String restaurant;

    private double avgReview;

    private List<ResponseReviewDto> reviews;



}
