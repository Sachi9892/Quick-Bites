package com.quick_bites.service.managers.order_manager.payment_manager.impl;


import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import com.quick_bites.exceptions.NoPaymentFoundException;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;



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

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", orderId);
        options.put("razorpay_payment_id", paymentId);
        options.put("razorpay_signature", signature);

        // Verify the signature using Razorpay secret key
        return Utils.verifyPaymentSignature(options, "TFT6Y0u3M619bQ1UOztqngsR");


    }

}
