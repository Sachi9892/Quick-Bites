package com.quick_bites.repository;

import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderRecord, Long> {

    OrderRecord  findByPaymentDetails( PaymentDetails paymentDetails);

}
