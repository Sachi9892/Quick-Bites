package com.quick_bites.service.managers.order_manager.order_service.schedule_order;


import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.exceptions.SlotNotAvailableOrExpireException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;


@Service
@Qualifier("scheduleOnlineOrderService")
public class OnlineScheduleOrderServiceImpl extends OrderBaseService implements IPlaceOrderFactory {

    private final ICreateRazorOrder createRazorOrder;

    private final TimeSlotVerificationService timeSlotVerificationService;

    public OnlineScheduleOrderServiceImpl(OrderRepository orderRepository,
                                          PaymentDetailsRepository paymentRepository,
                                          DeliveryAddressRepository deliveryAddressRepository,
                                          UserRepository userRepository, CartRepository cartRepository
            , TimeSlotVerificationService timeSlotVerificationService , ICreateRazorOrder createRazorOrder) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
        this.createRazorOrder = createRazorOrder;
        this.timeSlotVerificationService = timeSlotVerificationService;

    }

    @Override
    public OrderRecord placeOrder(PlaceOrderRequestDto requestDto) throws RazorpayException {


        //Verify if order placed at least 30 minutes before
        timeSlotVerificationService.validateOrderTiming(requestDto.getScheduledTime());


        // Create a new order
        OrderRecord newOrder = createOrderBase(requestDto);

        newOrder.setScheduledTime(requestDto.getScheduledTime().truncatedTo(ChronoUnit.SECONDS));

        newOrder.setOrderType(OrderType.SCHEDULE_ONLINE);
        newOrder.setOrderStatus(OrderStatus.PENDING);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        // Create Razorpay order and update PaymentDetails
        try {
            String razorpayOrderResponse = createRazorOrder.createRazorpayOrder(requestDto.getCartId());

            // Update PaymentDetails
            PaymentDetails payment = new PaymentDetails();
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentStatus(PaymentStatus.CREATED);
            payment.setModeOfPayment(PaymentMode.SCHEDULE_ONLINE);
            payment.setOrderRecord(savedOrder);
            payment.setCartId(requestDto.getCartId());
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
