package com.quik_bites.controller;

import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.dto.VerifyOtpDto;
import com.quik_bites.service.otp_manager.VerifyOtp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class OtpVerificationController {

    private final VerifyOtp validateOtpService;

    @PostMapping("/verify")
    public ResponseEntity<OtpResponseDto> verifyOtp(
            @RequestBody VerifyOtpDto verifyOtpDto) {

        OtpResponseDto response = validateOtpService.verifyOtp(verifyOtpDto.getMobileNumber(), verifyOtpDto.getOtp());
        HttpStatus status = response.getStatus() == OtpStatus.VERIFIED ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);

    }
}
