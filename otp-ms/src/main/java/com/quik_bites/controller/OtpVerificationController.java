package com.quik_bites.controller;

import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.service.otp_manager.VerifyOtp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class OtpVerificationController {

    private final VerifyOtp validateOtpService;

    @PostMapping("/verify")
    public ResponseEntity<OtpResponseDto> verifyOtp(
            @RequestParam String mobileNumber,
            @RequestParam String otp) {

        OtpResponseDto response = validateOtpService.verifyOtp(otp, mobileNumber);
        HttpStatus status = response.getStatus() == OtpStatus.VERIFIED ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);

    }
}
