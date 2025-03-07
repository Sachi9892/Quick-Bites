package com.quick_bites.controllers.order_controller.payment_controller;

import com.quick_bites.dto.paymentdto.PaymentVerificationDto;
import com.quick_bites.dto.paymentdto.VerificationApiResponse;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderStatus;
import com.quick_bites.exceptions.OrderNotFoundException;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.service.managers.order_manager.events.OrderPlaceEvent;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/checkout")
@CrossOrigin("*")
public class VerifyPaymentController {

    private final IVerifyPayment paymentVerificationService;
    private final OrderPlaceEvent orderPlaceEvent;
    private final OrderRepository orderRepository;

    public VerifyPaymentController(IVerifyPayment paymentVerificationService, OrderPlaceEvent orderPlaceEvent, OrderRepository orderRepository) {
        this.paymentVerificationService = paymentVerificationService;
        this.orderPlaceEvent = orderPlaceEvent;
        this.orderRepository = orderRepository;
    }

    @Value("${razorpay.secret.key}")
    private String secretKey;

    @PostMapping("/verify-payment")
    public ResponseEntity<VerificationApiResponse> verifyPayment(
            @RequestBody PaymentVerificationDto request ,
            @RequestHeader("X-API-Key") String apiKey) {

        // Internal security check
        if (!validateApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new VerificationApiResponse("Invalid API key", false));
        }

        // Call the verification service
        try {

            boolean isVerified = paymentVerificationService.verifyPaymentSignature(request.getOrderId(), request.getPaymentId(), request.getSignature());

            if (isVerified) {

                // Update order status
                OrderRecord order = orderRepository.findByRazorpayOrderId(request.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

                order.setOrderStatus(OrderStatus.PLACED);
                orderRepository.save(order);

                //Send the event to the Restaurant- ms, Rider-Ms
                orderPlaceEvent.sendOrderPlacedEvent(order);
                return ResponseEntity.ok(new VerificationApiResponse("Payment verified and order placed successfully" , true));

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new VerificationApiResponse("Payment failed and order placed " , false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new VerificationApiResponse("Error occurred while placing an order" , false));
        }

    }

    private boolean validateApiKey(String apiKey) {
        return secretKey.equals(apiKey);
    }


}
