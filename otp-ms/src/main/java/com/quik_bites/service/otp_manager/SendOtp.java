package com.quik_bites.service.otp_manager;

import com.quik_bites.dto.OtpCases;
import com.quik_bites.dto.OtpResponseDto;

public interface SendOtp {

    OtpResponseDto sendOtp(String mobileNumber , OtpCases cases);

}