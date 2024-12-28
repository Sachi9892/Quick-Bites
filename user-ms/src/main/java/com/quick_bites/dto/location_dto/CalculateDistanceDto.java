package com.quick_bites.dto.location_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateDistanceDto {

    private Double startLatitude;
    private Double startLongitude;

    private Double endLatitude;
    private Double endLongitude;
}
