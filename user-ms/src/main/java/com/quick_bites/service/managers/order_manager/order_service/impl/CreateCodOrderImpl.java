package com.quick_bites.service.managers.order_manager.order_service.impl;

import com.quick_bites.dto.orderdto.OrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.ICreateOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class CreateCodOrderImpl implements ICreateOrderService {

    private final OrderRepository orderRepository;
    private final PaymentDetailsRepository paymentRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public OrderRecord createOrder(OrderRequestDto orderRequestDto) {


        Long cartId = orderRequestDto.getCartId();


        DeliveryAddresses addresses = deliveryAddressRepository.findById(orderRequestDto.getDeliveryAddress())
                .orElseThrow(() -> new NoResourceFoundException("No address found"));

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoResourceFoundException("No cart"));

        OrderRecord newOrder = new OrderRecord();
        newOrder.setOrderType(OrderType.COD);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setCart(cart);
        newOrder.setDeliveryAddress(addresses);

        log.info("Setting order status to: {}", newOrder.getOrderStatus());

        User user = userRepository.findById(cart.getUserId()).orElseThrow(() -> new NoResourceFoundException("No User "));
        newOrder.setUser(user);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);


        PaymentDetails payment = new PaymentDetails();

        payment.setModeOfPayment("CASH_ON_DELIVERY");
        payment.setPaymentStatus(PaymentStatus.UNPAID);
        payment.setOrderRecord(savedOrder);
        payment.setCartId(cartId);
        payment.setTotalAmount(cart.getTotalAmount());

        // Skip Razorpay-specific fields for COD
        payment.setRazorpayOrderId(null);
        payment.setRazorpayPaymentId(null);
        payment.setRazorpaySignature(null);

        paymentRepository.save(payment);

        savedOrder.setPaymentDetails(payment);

        return savedOrder;

    }
}
