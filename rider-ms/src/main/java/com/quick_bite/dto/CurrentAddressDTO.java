package com.quick_bite.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CurrentAddressDTO {

    private Double latitude;
    private Double longitude;
    private String name;
    private String landmark;
    private String pinCode;

}
