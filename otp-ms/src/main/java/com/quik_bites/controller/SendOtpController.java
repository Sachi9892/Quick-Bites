package com.quik_bites.controller;


import com.quik_bites.dto.*;
import com.quik_bites.service.TwilioOTPService;
import com.quik_bites.service.otp_manager.SendOtp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class SendOtpController {

    private final SendOtp sendOtpService;

    @PostMapping("/send")

    public ResponseEntity<OtpResponseDto> sendOtp(@RequestBody RequestOtpDto requestOtpDto) {

        OtpResponseDto response = sendOtpService.sendOtp(requestOtpDto.getMobileNumber(), requestOtpDto.getCases());

        HttpStatus status = response.getStatus() == OtpStatus.DELIVERED ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        log.info("Status {} " , status);
        return new ResponseEntity<>(response, status);
    }

}