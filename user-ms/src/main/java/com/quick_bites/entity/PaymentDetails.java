package com.quick_bites.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private String transactionId;

    private Long userId;

    private Long restaurantId;

    private Long cartId;

    private PaymentStatus paymentStatus;

    private Double totalAmount;

    private String modeOfPayment;


    private String razorpayOrderId;    // To store the Razorpay order ID
    private String razorpayPaymentId;  // To store the Razorpay payment ID
    private String razorpaySignature;  // To store the signature received from Razorpay

    // Add a timestamp for when the payment was created or verified
    private LocalDateTime paymentTime;
}
