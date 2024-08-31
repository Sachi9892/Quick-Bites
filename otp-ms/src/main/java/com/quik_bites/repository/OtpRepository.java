package com.quik_bites.repository;

import com.quik_bites.entity.OtpRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpRecord , Long> {

    Optional<OtpRecord> findByMobileNumberAndOtp(String mobileNumber, String otp);

    Optional<OtpRecord> findTopByMobileNumberOrderByCreatedAtDesc(String mobileNumber);
}
