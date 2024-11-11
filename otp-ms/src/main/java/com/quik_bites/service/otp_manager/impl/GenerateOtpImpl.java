package com.quik_bites.service.otp_manager.impl;

import com.quik_bites.service.otp_manager.IGenerateOtp;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class GenerateOtpImpl implements IGenerateOtp {

    @Override
    public String generateOtp() {

        SecureRandom random = new SecureRandom();
        int otpNumber = 100000 + random.nextInt(900000);

        return String.valueOf(otpNumber);
    }
}
