package com.quick_bites.dto.location_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private double latitude;
    private double longitude;
    private String address;

}
