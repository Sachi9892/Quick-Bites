package com.quick_bites.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    private Long paymentId;

    private Long transactionId;

    private Long userId;

    private Long restaurantId;

    private PaymentStatus paymentStatus;

    private Double totalAmount;

    private String modeOfPayment;
}
