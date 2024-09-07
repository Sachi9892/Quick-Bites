package com.quick_bites.service.managers.order_manager.payment_manager.impl;

import com.quick_bites.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Service
@AllArgsConstructor
@Slf4j
public class GenerateSignatureServiceImpl {

    public String generateSignature(String orderId, String paymentId, String secret) {

        try {

            // Create the signature payload: order_id + "|" + razorpay_payment_id
            String payload = orderId + "|" +  paymentId;


            Mac mac = Mac.getInstance(AppConstants.ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), AppConstants.ALGORITHM);
            mac.init(secretKey);

            // Generate the hash
            byte[] hash = mac.doFinal(payload.getBytes());

            // Convert hash to base64 string
            String s = Base64.getEncoder().encodeToString(hash);

            log.info("Signature  :  {} " , s);

            return s;

        } catch (Exception e) {
            log.error("Error generating HMAC SHA-256 signature: ", e);
            return null;
        }

    }
}
