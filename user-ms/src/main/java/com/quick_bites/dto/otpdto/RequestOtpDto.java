package com.quick_bites.dto.otpdto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RequestOtpDto implements Serializable {

    private String mobileNumber;
    private OtpCases cases;

}
