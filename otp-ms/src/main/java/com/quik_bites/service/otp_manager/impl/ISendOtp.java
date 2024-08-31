package com.quik_bites.service.otp_manager.impl;

import com.quik_bites.config.TwilioConfig;
import com.quik_bites.dto.OtpCases;
import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.entity.OtpRecord;
import com.quik_bites.repository.OtpRepository;
import com.quik_bites.service.otp_manager.GenerateOtp;
import com.quik_bites.service.otp_manager.SendOtp;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@AllArgsConstructor
@Slf4j
public class ISendOtp implements SendOtp {

    private final GenerateOtp generateOtp;
    private final TwilioConfig twilioConfig;
    private final OtpRepository otpRepository;

    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    @Override
    public OtpResponseDto sendOtp(String mobileNumber, OtpCases cases) {

        try {
            // Generate otp
            String otp = generateOtp.generateOtp();

            //Store mobile number and otp
            otpStore.put(mobileNumber , otp);

            LocalDateTime now = LocalDateTime.now();

            // Expire in 10 minutes
            LocalDateTime expiresAt = now.plusMinutes(10);

            // Create obj of otp
            OtpRecord otpRecord = new OtpRecord(mobileNumber, otp, now, expiresAt, OtpStatus.PENDING, cases);

            // Save
            otpRepository.save(otpRecord);

            // Receiver (Customer, Restaurant, Rider)
            PhoneNumber to = new PhoneNumber(mobileNumber);

            // Sender (Quick - bites)
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            String otpMessage = "Your OTP is: " + otp;

            log.info("Otp is - {} " , otp);

            //Send otp
            Message message = Message.creator(to, from, otpMessage).create();

            log.info("Message : {} " , message);

            // Return success response
            return new OtpResponseDto(mobileNumber, OtpStatus.DELIVERED, otp ,"Otp sent!");

        } catch (Exception e) {
            log.error("Failed to send OTP to {}: {}", mobileNumber, e.getMessage());

            // Return failure response
            return new OtpResponseDto(mobileNumber, OtpStatus.FAILED, "", "Failed to send OTP: " + e.getMessage());
        }

    }


    //To get otp
    public String getOtp(String phoneNumber) {
        return otpStore.get(phoneNumber);
    }


    //To remove
    public void removeOtp(String phoneNumber) {
        otpStore.remove(phoneNumber);
    }

}
