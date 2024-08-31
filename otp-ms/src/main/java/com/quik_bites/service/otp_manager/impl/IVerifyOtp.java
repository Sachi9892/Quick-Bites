package com.quik_bites.service.otp_manager.impl;

import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.entity.OtpRecord;
import com.quik_bites.repository.OtpRepository;
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
    public OtpResponseDto verifyOtp(String mobileNumber, String otp) {

        Optional<OtpRecord> otpRecordOptional = otpRepository.findByMobileNumberAndOtp(mobileNumber, otp);

        if (otpRecordOptional.isEmpty()) {
            return new OtpResponseDto(mobileNumber , OtpStatus.FAILED , "" , "Failed ");
        }

        OtpRecord otpRecord = otpRecordOptional.get();

        // Check if OTP is expired
        if (otpRecord.getExpiresAt().isBefore(LocalDateTime.now())) {
            otpRecord.setStatus(OtpStatus.EXPIRED);
            otpRepository.save(otpRecord);
            return new OtpResponseDto(mobileNumber , OtpStatus.EXPIRED , "" , "Expired ");
        }

        // Check if the maximum number of attempts has been reached
        if (otpRecord.getAttemptCount() >= MAX_ATTEMPTS) {
            otpRecord.setStatus(OtpStatus.FAILED);
            otpRepository.save(otpRecord);
            return new OtpResponseDto(mobileNumber , OtpStatus.FAILED , "" , "Failed ");
        }

        // Verify OTP
        if (otpRecord.getOtp().equals(otp)) {
            // OTP is correct, mark as verified
            otpRecord.setStatus(OtpStatus.VERIFIED);
            otpRepository.save(otpRecord);
            return new OtpResponseDto(mobileNumber , OtpStatus.VERIFIED , "" , "Verified! ");
        }

        return new OtpResponseDto(mobileNumber , OtpStatus.FAILED , "" , "Failed ");

    }

}
