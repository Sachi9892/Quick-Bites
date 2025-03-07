package com.quick_bites.service.managers.order_manager.order_service.schedule_order;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Qualifier("scheduleCodOrderService")
public class CodScheduleOrderServiceImpl extends OrderBaseService implements IPlaceOrderFactory {

    private final TimeSlotVerificationService timeSlotVerificationService;

    public CodScheduleOrderServiceImpl(OrderRepository orderRepository,
                                       PaymentDetailsRepository paymentRepository,
                                       DeliveryAddressRepository deliveryAddressRepository
            , UserRepository userRepository, CartRepository cartRepository ,
                                       TimeSlotVerificationService timeSlotVerificationService) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
        this.timeSlotVerificationService = timeSlotVerificationService;
    }


    @Override
    public OrderRecord placeOrder(PlaceOrderRequestDto requestDto) throws RazorpayException {

        //Verify if the order is placed at least 30 minutes before
        timeSlotVerificationService.validateOrderTiming(requestDto.getScheduledTime());


        OrderRecord newOrder = createOrderBase(requestDto);

        newOrder.setScheduledTime(requestDto.getScheduledTime().truncatedTo(ChronoUnit.SECONDS));

        newOrder.setOrderType(OrderType.SCHEDULE_COD);
        newOrder.setOrderStatus(OrderStatus.PLACED);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        PaymentDetails payment = new PaymentDetails();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setModeOfPayment(PaymentMode.SCHEDULE_COD);
        payment.setPaymentStatus(PaymentStatus.UNPAID);
        payment.setOrderRecord(savedOrder);
        payment.setCartId(requestDto.getCartId());
        payment.setTotalAmount(newOrder.getTotalAmount());

        paymentRepository.save(payment);
        savedOrder.setPaymentDetails(payment);

        // Update cart status
        Cart cart = cartRepository.findById(requestDto.getCartId()).orElseThrow(() -> new CartNotFoundException("No cart"));
        cart.setStatus(CartStatus.ORDERED);
        Cart updatedCart = cartRepository.save(cart);

        savedOrder.setCart(updatedCart);

        return savedOrder;

    }
}
