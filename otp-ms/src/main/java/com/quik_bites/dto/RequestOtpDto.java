package com.quik_bites.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOtpDto {

    private String mobileNumber;
    private OtpCases cases;

}
