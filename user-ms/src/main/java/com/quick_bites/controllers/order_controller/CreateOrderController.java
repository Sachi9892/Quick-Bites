package com.quick_bites.controllers.order_controller;


import com.quick_bites.dto.paytmdto.PaymentRequestDto;
import com.quick_bites.dto.paytmdto.PaymentVerificationDto;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CreateOrderController {


    private final ICreateRazorOrder createRazorOrder;

    private final IVerifyPayment verifyPayment;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/orders/create")
    public ResponseEntity<?> createOrderMethod(@RequestBody PaymentRequestDto orderRequest) {
        try {
            String razorpayOrder = createRazorOrder.createRazorpayOrder(orderRequest.getAmount(), orderRequest.getReceiptId());
            return ResponseEntity.ok(razorpayOrder);  // Return order details to the frontend
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating Razorpay order");
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/orders/verify")
    public ResponseEntity<?> verifyPaymentMethod(@RequestBody PaymentVerificationDto request) {

        try {
            boolean isVerified = verifyPayment.verifyPaymentSignature(request.getOrderId(), request.getPaymentId(), request.getSignature());
            if (isVerified) {
                return ResponseEntity.ok("Payment verified successfully");
            } else {
                return ResponseEntity.status(400).body("Invalid payment signature");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error verifying payment");
        }

    }



}
