package com.quick_bites.service.managers.order_manager.payment_manager.impl;


import com.quick_bites.constants.AppConstants;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderStatus;
import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.entity.PaymentStatus;
import com.quick_bites.exceptions.OrderNotFoundException;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.IVerifyPayment;
import com.quick_bites.exceptions.NoPaymentFoundException;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class VerifyPaymentImpl implements IVerifyPayment {

    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderRepository orderRepository;


    @Override
    public boolean verifyPaymentSignature(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) throws RazorpayException {

        try {
            // Razorpay's official verification method
            boolean isValid = Utils.verifyPaymentSignature(
                    (JSONObject) Map.of(
                            "razorpay_order_id", razorpayOrderId,
                            "razorpay_payment_id", razorpayPaymentId,
                            "razorpay_signature", razorpaySignature
                    ),
                    AppConstants.RAZORPAY_SECRET_KEY
            );

            if (!isValid) {
                log.error("Invalid Razorpay signature for payment: {}", razorpayPaymentId);
                return false;
            }

            // Fetch payment details (optional, depends on your flow)
            PaymentDetails paymentDetails = paymentDetailsRepository.findByRazorpayOrderId(razorpayOrderId);
            if (paymentDetails == null) {
                throw new NoPaymentFoundException("Payment not found for order: " + razorpayOrderId);
            }

            // Update payment status
            paymentDetails.setPaymentStatus(PaymentStatus.PAID);

            paymentDetailsRepository.save(paymentDetails);

            // Update order status
            OrderRecord order = orderRepository.findByRazorpayOrderId(razorpayOrderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found"));

            order.setOrderStatus(OrderStatus.PLACED);

            orderRepository.save(order);

            return true;

        } catch (RazorpayException e) {
            log.error("Razorpay verification failed: {}", e.getMessage());
            return false;
        }
    }


}





