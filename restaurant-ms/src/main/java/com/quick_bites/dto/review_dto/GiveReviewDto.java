package com.quick_bites.dto.review_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class GiveReviewDto {

    private Long id; //Either can be the dish or restaurant
    private Double rating;
    private String comment;
    private Long userId;

}
