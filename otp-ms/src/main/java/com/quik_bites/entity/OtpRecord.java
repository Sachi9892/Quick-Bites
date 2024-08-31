package com.quik_bites.entity;

import com.quik_bites.dto.OtpCases;
import com.quik_bites.dto.OtpStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class OtpRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String mobileNumber;


    @Column(nullable = false)
    private String otp;


    @Column(nullable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private LocalDateTime expiresAt;


    @Column(nullable = false)
    private OtpStatus status;


    @Column(nullable = false)
    private OtpCases context;

    @Getter
    @Column(nullable = false)
    private int attemptCount;


    public OtpRecord(String mobileNumber, String otp,
                     LocalDateTime now, LocalDateTime expiresAt,
                     OtpStatus otpStatus, OtpCases cases) {

        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.createdAt = now;
        this.expiresAt = expiresAt;
        this.status =otpStatus;
        this.context = cases;
        this.attemptCount = 0;

    }

    public void incrementAttemptCount() {
        this.attemptCount++;
    }

}