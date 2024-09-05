package com.quick_bites.repository;

import com.quick_bites.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails , Long> {


    PaymentDetails findByRazorpayPaymentId(String paymentId);

    PaymentDetails findByRazorpayOrderId(String orderId);

}
