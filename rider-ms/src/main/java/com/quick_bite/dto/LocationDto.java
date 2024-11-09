package com.quick_bite.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LocationDto {

    private double latitude;
    private double longitude;
    private String address;

}
