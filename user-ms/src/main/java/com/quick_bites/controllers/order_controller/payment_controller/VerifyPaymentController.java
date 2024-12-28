package com.quick_bites.controllers.order_controller.payment_controller;

import com.quick_bites.dto.paymentdto.PaymentVerificationDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderStatus;
import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.exceptions.OrderNotFoundException;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.events.OrderPlaceEvent;
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
    private final OrderPlaceEvent orderPlaceEvent;
    private final OrderRepository orderRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/order/verify-payment")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationDto request) {

        PaymentDetails paymentDetails = paymentDetailsRepository.findByRazorpayOrderId(request.getOrderId());

        if (paymentDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment details not found");
        }

        // Call the verification service
        try {

            boolean isVerified = verifyPayment.verifyPaymentSignature(request.getPaymentId(), request.getSignature());

            if (isVerified) {

                // Update order status
                OrderRecord order = orderRepository.findById(paymentDetails.getOrderRecord().getOrderId())
                        .orElseThrow(() -> new OrderNotFoundException("Order not found"));

                order.setOrderStatus(OrderStatus.PLACED);
                orderRepository.save(order);

                //Send the event to the Restaurant-Ms , Rider-Ms
                orderPlaceEvent.sendOrderPlacedEvent(order);
                return ResponseEntity.ok("Payment verified and order placed successfully");

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment signature");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying payment");
        }

    }

}
