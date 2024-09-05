package com.quick_bites.controllers.order_controller;


import com.quick_bites.dto.paymentdto.CartPaymentDto;
import com.quick_bites.dto.paymentdto.PaymentRequestDto;
import com.quick_bites.dto.paymentdto.PaymentVerificationDto;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CreatePaymentController {

    private final ICreateRazorOrder createRazorOrder;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/order/place-order")
    public ResponseEntity<String> createOrderMethod(@RequestBody CartPaymentDto orderRequest) {

        try {

            String razorpayOrder = createRazorOrder.createRazorpayOrder(orderRequest);

            return ResponseEntity.ok(razorpayOrder);

        } catch (Exception e) {

            return ResponseEntity.status(500).body("Error creating Razorpay order");

        }

    }



}
