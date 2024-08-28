package com.quick_bites.dto.reviewdto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiveReviewDto {

    private Double rating;
    private String comment;
    private Long userId;

}
