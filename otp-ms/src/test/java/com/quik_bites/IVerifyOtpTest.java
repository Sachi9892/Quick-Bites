package com.quik_bites;

import com.quik_bites.dto.OtpCases;
import com.quik_bites.dto.OtpStatus;
import com.quik_bites.dto.OtpResponseDto;
import com.quik_bites.entity.OtpRecord;
import com.quik_bites.repository.OtpRepository;
import com.quik_bites.service.otp_manager.impl.IVerifyOtp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class IVerifyOtpTest {

    @Mock
    private OtpRepository otpRepository;

    @InjectMocks
    private IVerifyOtp verifyOtpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerifyOtp_Success() {
        LocalDateTime now = LocalDateTime.now();
        OtpRecord otpRecord = new OtpRecord(
                1L, // Assuming ID is auto-generated
                "919892077388",
                "703329",
                now.minusMinutes(1),
                now.plusMinutes(10),// attempt// context// expires_at
                OtpStatus.PENDING ,
                OtpCases.USER_LOGIN ,
                0
        );
        when(otpRepository.findLatestOtpByMobileNumber("919892077388")).thenReturn(otpRecord);

        OtpResponseDto response = verifyOtpService.verifyOtp("919892077388", "703329");

        assertEquals(OtpStatus.VERIFIED, response.getStatus());
        assertEquals("Verified!", response.getMessage());
        verify(otpRepository, times(1)).save(otpRecord);
    }

    @Test
    void testVerifyOtp_Expired() {
        LocalDateTime now = LocalDateTime.now();
        OtpRecord otpRecord = new OtpRecord(
                1L, // Assuming ID is auto-generated
                "919892077388",
                "703329",
                now.minusMinutes(1),
                now.plusMinutes(10),// attempt// context// expires_at
                OtpStatus.PENDING ,
                OtpCases.USER_LOGIN ,
                0
        );
        when(otpRepository.findLatestOtpByMobileNumber("919892077388")).thenReturn(otpRecord);

        OtpResponseDto response = verifyOtpService.verifyOtp("919892077388", "703329");

        assertEquals(OtpStatus.EXPIRED, response.getStatus());
        assertEquals("Expired", response.getMessage());
        verify(otpRepository, times(1)).save(otpRecord);
    }

    @Test
    void testVerifyOtp_MaxAttemptsReached() {
        LocalDateTime now = LocalDateTime.now();
        OtpRecord otpRecord = new OtpRecord(
                1L, // Assuming ID is auto-generated
                "919892077388",
                "703329",
                now.minusMinutes(1),
                now.plusMinutes(10),// attempt// context// expires_at
                OtpStatus.PENDING ,
                OtpCases.USER_LOGIN ,
                0
        );
        when(otpRepository.findLatestOtpByMobileNumber("919892077388")).thenReturn(otpRecord);

        OtpResponseDto response = verifyOtpService.verifyOtp("919892077388", "703329");

        assertEquals(OtpStatus.FAILED, response.getStatus());
        assertEquals("You have reached the maximum number of attempts. Please request a new OTP.", response.getMessage());
        verify(otpRepository, times(1)).save(otpRecord);
    }

    @Test
    void testVerifyOtp_IncorrectOtp() {
        LocalDateTime now = LocalDateTime.now();
        OtpRecord otpRecord = new OtpRecord(
                1L, // Assuming ID is auto-generated
                "919892077388",
                "703329",
                now.minusMinutes(1),
                now.plusMinutes(10),// attempt// context// expires_at
                OtpStatus.PENDING ,
                OtpCases.USER_LOGIN ,
                0
        );
        when(otpRepository.findLatestOtpByMobileNumber("919892077388")).thenReturn(otpRecord);

        OtpResponseDto response = verifyOtpService.verifyOtp("919892077388", "654321");

        assertEquals(OtpStatus.FAILED, response.getStatus());
        assertEquals("Invalid OTP. Please try again.", response.getMessage());
        verify(otpRepository, times(1)).save(otpRecord);
    }

    @Test
    void testVerifyOtp_NoOtpFound() {
        when(otpRepository.findLatestOtpByMobileNumber("919892077388")).thenReturn(null);

        OtpResponseDto response = verifyOtpService.verifyOtp("919892077388", "703329");

        assertEquals(OtpStatus.FAILED, response.getStatus());
        assertEquals("Failed", response.getMessage());
        verify(otpRepository, never()).save(any(OtpRecord.class));
    }
}
