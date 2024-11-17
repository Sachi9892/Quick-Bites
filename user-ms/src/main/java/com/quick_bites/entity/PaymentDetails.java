package com.quick_bites.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    private Long cartId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMode modeOfPayment;

    @OneToOne(mappedBy = "paymentDetails")
    private OrderRecord orderRecord;


    private String razorpayOrderId;    // To store the Razorpay order ID
    private String razorpayPaymentId;  // To store the Razorpay payment ID
    private String razorpaySignature;  // To store the signature received from Razorpay

    // Add a timestamp for when the payment was created or verified
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime paymentTime;
}
