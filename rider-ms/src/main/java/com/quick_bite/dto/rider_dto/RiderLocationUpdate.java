package com.quick_bite.dto.rider_dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderLocationUpdate implements Serializable {

    private double latitude;
    private double longitude;

}
