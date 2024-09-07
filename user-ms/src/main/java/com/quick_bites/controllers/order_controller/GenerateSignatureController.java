package com.quick_bites.controllers.order_controller;


import com.quick_bites.constants.AppConstants;
import com.quick_bites.service.managers.order_manager.payment_manager.impl.GenerateSignatureServiceImpl;
import com.quick_bites.service.managers.order_manager.payment_manager.impl.VerifyPaymentImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/payment")
@AllArgsConstructor
public class GenerateSignatureController {

    private final GenerateSignatureServiceImpl verifyPaymentImpl;

    @GetMapping("/generate-signature")

    public ResponseEntity<String> generateSignature(
            @RequestParam("orderId") String orderId,
            @RequestParam("paymentId") String paymentId) {

        try {
            // Generate the signature
            String secret = AppConstants.RAZORPAY_SECRET_KEY;

            String signature = verifyPaymentImpl.generateSignature(orderId, paymentId, secret);

            if (signature != null) {
                return ResponseEntity.ok(signature);
            } else {
                return ResponseEntity.status(500).body("Error generating signature");
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Exception occurred: " + e.getMessage());
        }
    }

}
