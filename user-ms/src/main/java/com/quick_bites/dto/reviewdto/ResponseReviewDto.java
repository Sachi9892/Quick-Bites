package com.quick_bites.dto.reviewdto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseReviewDto {

    private Long id;
    private Double rating;
    private String comment;
    private LocalDateTime reviewTime;
}
