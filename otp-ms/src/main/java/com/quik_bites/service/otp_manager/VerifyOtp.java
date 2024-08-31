package com.quik_bites.service.otp_manager;

import com.quik_bites.dto.OtpResponseDto;

public interface VerifyOtp {

    OtpResponseDto verifyOtp(String mobileNumber, String otp);

}
