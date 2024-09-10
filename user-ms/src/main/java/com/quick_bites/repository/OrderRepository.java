package com.quick_bites.repository;

import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<OrderRecord, Long> {

    OrderRecord  findByPaymentDetails( PaymentDetails paymentDetails);

    List<OrderRecord> findByRestId(Long restId);

    List<OrderRecord> findByUser_UserId(Long userId);

}
