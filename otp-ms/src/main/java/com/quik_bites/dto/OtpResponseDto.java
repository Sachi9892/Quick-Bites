package com.quik_bites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpResponseDto {

    private String mobileNumber;
    private OtpStatus status;
    private String otp;
    private String message;

}
