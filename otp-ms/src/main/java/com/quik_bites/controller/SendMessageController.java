package com.quik_bites.controller;


import com.quik_bites.dto.SendMessageDto;
import com.quik_bites.service.otp_manager.ISendMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
@AllArgsConstructor
@Slf4j
public class SendMessageController {

    private final ISendMessageService sendMessageService;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageDto sendMessageDto) {
        boolean sent = sendMessageService.sendMessage(sendMessageDto.getMobileNumber(), sendMessageDto.getMessage());
        if(sent) {
            log.info("Inside otp-ms controller");
            ResponseEntity.ok("Message sent successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Twilio Is Down");
    }
}
