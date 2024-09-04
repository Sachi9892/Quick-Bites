package com.quik_bites.service.otp_manager.impl;

import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.entity.OtpRecord;
import com.quik_bites.repository.OtpRepository;
import com.quik_bites.service.otp_manager.SendOtp;
import com.quik_bites.service.otp_manager.VerifyOtp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class IVerifyOtp implements VerifyOtp {

    private final OtpRepository otpRepository;

    private static final int MAX_ATTEMPTS = 3;

    @Override
    public OtpResponseDto verifyOtp(String mobileNumber, String userOtp) {

        OtpRecord sentOtp = otpRepository.findLatestOtpByMobileNumber(mobileNumber);

        log.info("Sent OTP: {}", sentOtp);

        if (sentOtp == null) {
            log.info("No OTP record found for mobile number: {}", mobileNumber);
            return new OtpResponseDto(OtpStatus.FAILED, "Failed");
        }

        // Check if OTP is expired
        if (sentOtp.getExpiresAt().isBefore(LocalDateTime.now())) {
            sentOtp.setStatus(OtpStatus.EXPIRED);
            otpRepository.save(sentOtp);
            log.info("OTP has expired.");
            return new OtpResponseDto( OtpStatus.EXPIRED, "Expired");
        }

        // Check if the maximum number of attempts has been reached
        if (sentOtp.getAttemptCount() >= MAX_ATTEMPTS) {
            sentOtp.setStatus(OtpStatus.FAILED);
            otpRepository.save(sentOtp);
            log.info("Maximum OTP attempt limit reached.");
            return new OtpResponseDto( OtpStatus.FAILED,
                    "You have reached the maximum number of attempts. " + "Please request a new OTP.");
        }

        // Verify OTP
        if (sentOtp.getOtp().equalsIgnoreCase(userOtp)) {
            // OTP is correct, mark as verified
            sentOtp.setStatus(OtpStatus.VERIFIED);
            otpRepository.save(sentOtp);
            log.info("OTP verified successfully.");
            return new OtpResponseDto(OtpStatus.VERIFIED,  "Verified !");
        }

        // Incorrect OTP: Increment attempt count and mark OTP as failed if incorrect
        sentOtp.setAttemptCount(sentOtp.getAttemptCount() + 1);
        otpRepository.save(sentOtp);
        log.info("Incorrect OTP. Attempt count incremented.");
        return new OtpResponseDto(OtpStatus.FAILED,  "Invalid OTP. Please try again.");

    }

}
