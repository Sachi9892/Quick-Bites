package com.quick_bites.dto.restaurant_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseRestDto implements Serializable {

    private String restaurantName;
    private String mobileNumber;
    private Double avgReview;
    private Integer totalRating;

}
