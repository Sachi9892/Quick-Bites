package com.quick_bites.dto.otpdto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VerifyOtpDto {

    private String mobileNumber;
    private String otp;

}
