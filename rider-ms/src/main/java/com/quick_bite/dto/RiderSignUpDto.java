package com.quick_bite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RiderSignUpDto {

    private String name;
    private String mobileNumber;
    private String email;
    private String currentAddress;

}
