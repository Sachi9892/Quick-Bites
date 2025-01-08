package com.quick_bite.dto.rider_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiderDetails {

    private String riderId;
    private double latitude;
    private double longitude;
    private String status;

}
