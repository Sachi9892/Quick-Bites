package com.quick_bites.service.managers.dish_rendering_manager.feign_client;


import com.quick_bites.dto.notificationdto.SendMessageDto;
import com.quick_bites.dto.otpdto.OtpResponseDto;
import com.quick_bites.dto.otpdto.RequestOtpDto;
import com.quick_bites.dto.otpdto.VerifyOtpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "OTP-MS")
public interface OtpClient {

    @PostMapping("/user/send")
    ResponseEntity<OtpResponseDto> sendOtp(@RequestBody RequestOtpDto requestOtpDto);


    @PostMapping("/user/verify")
    ResponseEntity<OtpResponseDto> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto);

    @PostMapping("/send/message")
    ResponseEntity<String> sendMessage(@RequestBody SendMessageDto sendMessageDto);


}
