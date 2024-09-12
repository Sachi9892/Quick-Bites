package com.quick_bites.service.managers.order_manager.order_service.order_now.impl;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CodOrderNowServiceImpl  extends OrderBaseService implements ICreateOrderNowService {

    public CodOrderNowServiceImpl(OrderRepository orderRepository, PaymentDetailsRepository paymentRepository, DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository, CartRepository cartRepository) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
    }

    @Override
    public OrderRecord createOrder(PlaceOrderRequestDto placeOrderRequestDto) throws RazorPayException {

        OrderRecord newOrder = createOrderBase(placeOrderRequestDto);
        newOrder.setOrderType(OrderType.COD);
        newOrder.setOrderStatus(OrderStatus.PLACED);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        PaymentDetails payment = new PaymentDetails();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setModeOfPayment("CASH_ON_DELIVERY");
        payment.setPaymentStatus(PaymentStatus.UNPAID);
        payment.setOrderRecord(savedOrder);
        payment.setCartId(placeOrderRequestDto.getCartId());
        payment.setTotalAmount(newOrder.getTotalAmount());

        paymentRepository.save(payment);
        savedOrder.setPaymentDetails(payment);

        // Update cart status
        Cart cart = cartRepository.findById(placeOrderRequestDto.getCartId()).orElseThrow(() -> new CartNotFoundException("No cart"));
        cart.setStatus(CartStatus.ORDERED);
        cartRepository.save(cart);

        return savedOrder;
    }
}
