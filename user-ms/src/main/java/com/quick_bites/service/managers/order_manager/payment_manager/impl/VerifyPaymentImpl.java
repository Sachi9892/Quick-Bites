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


@Service
@AllArgsConstructor
@Slf4j
public class VerifyPaymentImpl implements IVerifyPayment {

    private final PaymentDetailsRepository paymentDetailsRepository;
    private final GenerateSignatureServiceImpl generateSignatureService;

    @Override
    public boolean verifyPaymentSignature(String paymentId, String signature) throws RazorpayException {

        PaymentDetails paymentDetails = paymentDetailsRepository.findByRazorpayPaymentId(paymentId);

        if (paymentDetails == null) {
            throw new NoPaymentFoundException("Payment details not found for paymentId: " + paymentId);
        }

        String orderId = paymentDetails.getRazorpayOrderId();
        String secret = AppConstants.RAZORPAY_SECRET_KEY;

        // Generate the signature
        String generatedSignature = generateSignatureService.generateSignature(orderId, paymentId, secret);

        // Compare the generated signature with the received signature
        if (generatedSignature != null && generatedSignature.equals(signature)) {
            // Update the payment details only after successful verification
            paymentDetails.setRazorpayPaymentId(paymentId);
            paymentDetails.setRazorpayOrderId(orderId);
            paymentDetails.setRazorpaySignature(generatedSignature);
            paymentDetails.setPaymentStatus(PaymentStatus.PAID);

            String ans = paymentDetails.toString();
            log.info("Payment info: {}", ans);

            // Save the updated payment details
            paymentDetailsRepository.save(paymentDetails);

            return true;

        } else {
            log.error("Payment signature verification failed for paymentId: {}", paymentId);
            return false;
        }

    }

}





