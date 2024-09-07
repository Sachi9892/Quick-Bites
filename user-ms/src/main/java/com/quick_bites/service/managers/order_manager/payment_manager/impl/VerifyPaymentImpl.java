package com.quick_bites.service.managers.order_manager.payment_manager.impl;


import com.quick_bites.constants.AppConstants;
import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.entity.PaymentStatus;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import com.quick_bites.exceptions.NoPaymentFoundException;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Service
@AllArgsConstructor
@Slf4j
public class VerifyPaymentImpl implements IVerifyPayment {

    private final PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public boolean verifyPaymentSignature(String paymentId, String signature) throws RazorpayException {

        PaymentDetails paymentDetails = paymentDetailsRepository.findByRazorpayPaymentId(paymentId);

        if (paymentDetails == null) {
            throw new NoPaymentFoundException("Payment details not found for paymentId: " + paymentId);
        }

        String orderId = paymentDetails.getRazorpayOrderId();
        String secret = AppConstants.RAZORPAY_SECRET_KEY;

        // Create the signature payload: order_id + "|" + razorpay_payment_id
        String payload = orderId + "|" + paymentId;

        try {
            // Generate HMAC SHA-256 signature
            Mac mac = Mac.getInstance(AppConstants.ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), AppConstants.ALGORITHM);
            mac.init(secretKey);

            // Generate the hash
            byte[] hash = mac.doFinal(payload.getBytes());

            // Convert hash to base64 string
            String generatedSignature = Base64.getEncoder().encodeToString(hash);


            // Compare the generated signature with the received signature
            if (generatedSignature.equals(signature)) {

                // Update the payment details only after successful verification
                paymentDetails.setRazorpayPaymentId(paymentId);

                paymentDetails.setRazorpayOrderId(orderId);
                paymentDetails.setRazorpaySignature(generatedSignature);
                paymentDetails.setPaymentStatus(PaymentStatus.PAID);

                String ans = paymentDetails.toString();
                log.info(" Payment info {} " , ans);

                // Save the updated payment details
                paymentDetailsRepository.save(paymentDetails);

                return true;

            } else {
                log.error("Payment signature verification failed for paymentId: {}", paymentId);

                return false;
            }

        } catch (Exception e) {
            log.error("Error generating HMAC SHA-256 signature: ", e);
            return false;
        }

    }

}
