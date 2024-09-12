package com.quick_bites.service.managers.order_manager.order_service.order_now.impl;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OnlineOrderNowServiceImpl extends OrderBaseService implements ICreateOrderNowService {

    private final ICreateRazorOrder createRazorpayOrder;


    public OnlineOrderNowServiceImpl(OrderRepository orderRepository, PaymentDetailsRepository paymentRepository, DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository, CartRepository cartRepository , ICreateRazorOrder createRazorpayOrder) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
        this.createRazorpayOrder = createRazorpayOrder;
    }


    @Override
    public OrderRecord createOrder(PlaceOrderRequestDto placeOrderRequestDto) throws RazorPayException {


        OrderRecord newOrder = createOrderBase(placeOrderRequestDto);
        newOrder.setOrderType(OrderType.ONLINE);
        newOrder.setOrderStatus(OrderStatus.PENDING);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        // Create Razorpay order
        try {

            String razorpayOrderResponse = createRazorpayOrder.createRazorpayOrder(placeOrderRequestDto.getCartId());
            PaymentDetails payment = new PaymentDetails();
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentStatus(PaymentStatus.CREATED);
            payment.setModeOfPayment("ONLINE");
            payment.setOrderRecord(savedOrder);
            payment.setCartId(placeOrderRequestDto.getCartId());
            payment.setTotalAmount(newOrder.getTotalAmount());
            payment.setRazorpayOrderId(new JSONObject(razorpayOrderResponse).getString("id"));

            PaymentDetails savedPayment = paymentRepository.save(payment);
            savedOrder.setPaymentDetails(savedPayment);

            return orderRepository.save(savedOrder);

        } catch (Exception e) {
            throw new RazorPayException("Razorpay is down currently!");
        }

    }

}
