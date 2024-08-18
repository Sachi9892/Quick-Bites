package com.quick_bites.dto.review_dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviewDto {

    private double rating;
    private String comment;

}
