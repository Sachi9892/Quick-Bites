package com.quick_bites.dto.otpdto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestOtpDto {

    private String mobileNumber;
    private OtpCases cases;

}
