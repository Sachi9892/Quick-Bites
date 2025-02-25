package com.quick_bites.dto.location_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateDistanceDto implements Serializable {

    private Double startLatitude;
    private Double startLongitude;

    private Double endLatitude;
    private Double endLongitude;

}
