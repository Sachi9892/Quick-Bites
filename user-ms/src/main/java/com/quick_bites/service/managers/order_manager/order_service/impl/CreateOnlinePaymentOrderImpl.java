package com.quick_bites.service.managers.order_manager.order_service.impl;


import com.quick_bites.dto.orderdto.OrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.ICreateOrderService;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;



@Service
@AllArgsConstructor
@Slf4j
public class CreateOnlinePaymentOrderImpl implements ICreateOrderService {


    private final OrderRepository orderRepository;
    private final PaymentDetailsRepository paymentRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ICreateRazorOrder createRazorOrder;

    @Override
    public OrderRecord createOrder(OrderRequestDto orderRequestDto) {


        Long cartId = orderRequestDto.getCartId();
        DeliveryAddresses addresses = deliveryAddressRepository.findById(orderRequestDto.getDeliveryAddress())
                .orElseThrow(() -> new NoResourceFoundException("No Address found"));

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoResourceFoundException("No cart"));

        OrderRecord newOrder = new OrderRecord();
        newOrder.setOrderType(OrderType.ONLINE);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setCart(cart);
        newOrder.setDeliveryAddress(addresses);

        log.info("Order before saving user : {} " , newOrder);

        User user = userRepository.findById(cart.getUserId()).orElseThrow(() -> new NoResourceFoundException("No User "));
        newOrder.setUser(user);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        // Now create Razorpay order and update PaymentDetails
        try {
            String razorpayOrderResponse = createRazorOrder.createRazorpayOrder(cartId);

            // Update PaymentDetails
            PaymentDetails payment = new PaymentDetails();
            payment.setPaymentStatus(PaymentStatus.CREATED);
            payment.setOrderRecord(savedOrder);
            payment.setCartId(cartId);
            payment.setTotalAmount(cart.getTotalAmount());
            payment.setRazorpayOrderId(new JSONObject(razorpayOrderResponse).getString("id"));

            // Save the payment
            PaymentDetails savedPayment = paymentRepository.save(payment);

            savedOrder.setPaymentDetails(savedPayment);

            // Update the OrderRecord with payment information
            OrderRecord save = orderRepository.save(savedOrder);

            log.info("Final order : {} " , save);

            return savedOrder;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }

    }
}
