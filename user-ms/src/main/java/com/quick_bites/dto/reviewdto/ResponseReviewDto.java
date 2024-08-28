package com.quick_bites.dto.reviewdto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseReviewDto {

    private double rating;
    private String comment;

}
