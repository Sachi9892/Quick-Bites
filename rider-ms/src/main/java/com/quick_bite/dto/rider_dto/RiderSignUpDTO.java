package com.quick_bite.dto.rider_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RiderSignUpDTO {

    private String name;
    private String mobileNumber;
    private String email;
    private String address;

}
