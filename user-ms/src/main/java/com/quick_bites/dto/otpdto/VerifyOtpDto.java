package com.quick_bites.dto.otpdto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class VerifyOtpDto implements Serializable {

    private String mobileNumber;
    private String otp;

}
