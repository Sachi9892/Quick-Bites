package com.quick_bites.service.managers.order_manager.order_service.schedule_order.impl;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.exceptions.SlotNotAvailableOrExpireException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.ICreateScheduleOrder;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.SlotTimingService;

import java.util.UUID;

public class CodScheduleOrderServiceImpl extends OrderBaseService implements ICreateScheduleOrder {

    private final SlotTimingService slotTimingService;

    public CodScheduleOrderServiceImpl(OrderRepository orderRepository, PaymentDetailsRepository paymentRepository, DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository, CartRepository cartRepository , SlotTimingService slotTimingService) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
        this.slotTimingService = slotTimingService;
    }

    @Override
    public OrderRecord createScheduleOrder(PlaceOrderRequestDto scheduleOrderDto) throws RazorPayException {

        if (!slotTimingService.isValidSlot(scheduleOrderDto.getScheduledTime())) {
            throw new SlotNotAvailableOrExpireException("Slot Unavailable Or Expired!");
        }

        OrderRecord newOrder = createOrderBase(scheduleOrderDto);
        newOrder.setOrderType(OrderType.COD);
        newOrder.setOrderStatus(OrderStatus.PLACED);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        PaymentDetails payment = new PaymentDetails();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setModeOfPayment("CASH_ON_DELIVERY");
        payment.setPaymentStatus(PaymentStatus.UNPAID);
        payment.setOrderRecord(savedOrder);
        payment.setCartId(scheduleOrderDto.getCartId());
        payment.setTotalAmount(newOrder.getTotalAmount());

        paymentRepository.save(payment);
        savedOrder.setPaymentDetails(payment);

        // Update cart status
        Cart cart = cartRepository.findById(scheduleOrderDto.getCartId()).orElseThrow(() -> new CartNotFoundException("No cart"));
        cart.setStatus(CartStatus.ORDERED);
        cartRepository.save(cart);

        return savedOrder;
    }
}
