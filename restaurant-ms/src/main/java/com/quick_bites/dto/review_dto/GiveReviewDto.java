package com.quick_bites.dto.review_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GiveReviewDto implements Serializable {

    private Long id; //Either can be the dish or restaurant
    private Double rating;
    private String comment;
    private Long userId;

}
