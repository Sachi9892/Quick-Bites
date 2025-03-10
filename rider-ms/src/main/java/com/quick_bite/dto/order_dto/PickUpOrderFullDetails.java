package com.quick_bite.dto.order_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PickUpOrderFullDetails implements Serializable {

    private Long orderId;
    private String riderId;


    private String restaurantName;
    private String restaurantAddress;

    private Double restaurantLatitude;
    private Double restaurantLongitude;
    private Double userLatitude;
    private Double userLongitude;

    private double pickUpDistance;
    private double dropDistance;
    private double earning;

}
