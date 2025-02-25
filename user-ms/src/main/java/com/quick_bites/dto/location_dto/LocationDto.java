package com.quick_bites.dto.location_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto implements Serializable {

    private double latitude;
    private double longitude;
    private String address;

}
