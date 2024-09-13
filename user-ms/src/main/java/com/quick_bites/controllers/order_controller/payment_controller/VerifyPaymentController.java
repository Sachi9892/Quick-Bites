package com.quick_bites.controllers.order_controller.payment_controller;

import com.quick_bites.dto.paymentdto.PaymentVerificationDto;
import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class VerifyPaymentController {

    private final IVerifyPayment verifyPayment;
    private final PaymentDetailsRepository paymentDetailsRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/order/verify-payment")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationDto request) {

        PaymentDetails paymentDetails = paymentDetailsRepository.findByRazorpayOrderId(request.getOrderId());

        if (paymentDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment details not found");
        }

        paymentDetailsRepository.save(paymentDetails);

        // Call the verification service
        try {
            boolean isVerified = verifyPayment.verifyPaymentSignature(request.getPaymentId(), request.getSignature());
            if (isVerified) {
                return ResponseEntity.ok("Payment verified successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment signature");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying payment");
        }

    }

}
