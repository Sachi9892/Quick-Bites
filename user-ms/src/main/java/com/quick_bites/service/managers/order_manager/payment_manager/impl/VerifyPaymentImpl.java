package com.quick_bites.service.managers.order_manager.payment_manager.impl;


import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Service
@AllArgsConstructor
public class VerifyPaymentImpl implements IVerifyPayment {


    @Override
    public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) throws RazorpayException {

        String data = orderId + "|" + paymentId;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(data.getBytes());

            byte[] hash = digest.digest();
            String expectedSignature = byteToHex(hash);

            return expectedSignature.equals(signature);  // Compare signatures
        } catch (NoSuchAlgorithmException e) {
            throw new RazorpayException("Error while verifying payment signature", e);
        }
    }

    private String byteToHex(final byte[] hash) {

        try (Formatter formatter = new Formatter()) {

            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        }
    }


}
