package com.quick_bite.dto.rider_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiderDetails implements Serializable {

    private String riderId;
    private double latitude;
    private double longitude;
    private String status;

}
