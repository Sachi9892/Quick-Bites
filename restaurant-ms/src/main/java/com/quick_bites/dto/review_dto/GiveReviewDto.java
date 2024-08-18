package com.quick_bites.dto.review_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class GiveReviewDto {

    private double rating;
    private String comment;
    private LocalDateTime reviewTime;

}
