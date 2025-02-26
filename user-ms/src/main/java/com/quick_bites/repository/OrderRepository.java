package com.quick_bites.repository;

import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository  extends JpaRepository<OrderRecord, Long> {

    // Query to find Order by Razorpay order ID through PaymentDetails
    @Query("SELECT o FROM OrderRecord o WHERE o.paymentDetails.razorpayOrderId = :razorpayOrderId")
    Optional<OrderRecord> findByRazorpayOrderId(@Param("razorpayOrderId") String razorpayOrderId);

}
