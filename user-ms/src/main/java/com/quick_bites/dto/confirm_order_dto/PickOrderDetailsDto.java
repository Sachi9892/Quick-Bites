package com.quick_bites.dto.confirm_order_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PickOrderDetailsDto implements Serializable {

    private Long orderId;

    private String restaurantName;
    private String restaurantAddress;
    private Double restaurantLatitude;
    private Double restaurantLongitude;

    private Double userLatitude;
    private Double userLongitude;


}
