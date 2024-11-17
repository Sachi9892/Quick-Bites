package com.quick_bites.dto.reviewdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor  @NoArgsConstructor
public class ResponseReviewDto implements Serializable {

    private Long id;
    private Double rating;
    private String comment;

}
