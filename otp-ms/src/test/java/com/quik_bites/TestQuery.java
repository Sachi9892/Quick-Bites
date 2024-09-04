package com.quik_bites;


import com.quik_bites.entity.OtpRecord;
import com.quik_bites.repository.OtpRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
 class TestQuery {


 @Autowired
 private OtpRepository otpRepository;

 @Test
 void testFindByMobileNumberAndOtp() {

  String mobileNumber = "  919892077388";


 OtpRecord otpRecord = otpRepository.findLatestOtpByMobileNumber(mobileNumber);

  System.out.println(otpRecord == null ? "No record found" : "Otp absent");

     assert otpRecord != null;

     String record = otpRecord.toString();

  System.out.println("Found OTP Record: " + record);


 }

}
