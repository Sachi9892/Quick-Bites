package com.quick_bites.dto.reviewdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiveReviewDto implements Serializable {

    private Long id; //Either can be the dish or restaurant
    private Double rating;
    private String comment;
    private Long userId;

}
