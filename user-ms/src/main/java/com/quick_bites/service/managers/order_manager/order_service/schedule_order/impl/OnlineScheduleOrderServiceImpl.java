package com.quick_bites.service.managers.order_manager.order_service.schedule_order.impl;


import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.exceptions.SlotNotAvailableOrExpireException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.ICreateScheduleOrder;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.SlotTimingService;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OnlineScheduleOrderServiceImpl extends OrderBaseService implements ICreateScheduleOrder {

    private final ICreateRazorOrder createRazorOrder;
    private final SlotTimingService slotTimingService;

    public OnlineScheduleOrderServiceImpl(OrderRepository orderRepository, PaymentDetailsRepository paymentRepository, DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository, CartRepository cartRepository , SlotTimingService slotTimingService , ICreateRazorOrder createRazorOrder) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
        this.createRazorOrder = createRazorOrder;
        this.slotTimingService = slotTimingService;

    }

    @Override
    public OrderRecord createScheduleOrder(PlaceOrderRequestDto scheduleOrderDto) throws RazorPayException {


        if (!slotTimingService.isValidSlot(scheduleOrderDto.getScheduledTime())) {
            throw new SlotNotAvailableOrExpireException("Slot Unavailable Or Expired!");
        }

        // Create a new order
        OrderRecord newOrder = createOrderBase(scheduleOrderDto);
        newOrder.setScheduledTime(scheduleOrderDto.getScheduledTime());
        newOrder.setOrderType(OrderType.SCHEDULE_ONLINE);
        newOrder.setOrderStatus(OrderStatus.PENDING);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        // Create Razorpay order and update PaymentDetails
        try {
            String razorpayOrderResponse = createRazorOrder.createRazorpayOrder(scheduleOrderDto.getCartId());

            // Update PaymentDetails
            PaymentDetails payment = new PaymentDetails();
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentStatus(PaymentStatus.CREATED);
            payment.setModeOfPayment("ONLINE");
            payment.setOrderRecord(savedOrder);
            payment.setCartId(scheduleOrderDto.getCartId());
            payment.setTotalAmount(savedOrder.getTotalAmount());
            payment.setRazorpayOrderId(new JSONObject(razorpayOrderResponse).getString("id"));

            // Save the payment
            PaymentDetails savedPayment = paymentRepository.save(payment);
            savedOrder.setPaymentDetails(savedPayment);

            // Save the updated order with payment information
            return orderRepository.save(savedOrder);

        } catch (Exception e) {
            throw new RazorPayException("Razorpay is down currently!");
        }
    }

}
