package com.quick_bites.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseReviewDto {

    private double rating;
    private String comment;

}
