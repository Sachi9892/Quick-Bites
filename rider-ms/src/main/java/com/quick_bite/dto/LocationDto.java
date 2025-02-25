package com.quick_bite.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class LocationDto implements Serializable {

    private double latitude;
    private double longitude;
    private String address;

}
