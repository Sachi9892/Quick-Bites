package com.quick_bites.service.managers.order_manager.order_service.order_now.impl;


import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class CreateOnlinePaymentOrderNowImpl implements ICreateOrderNowService {


    private final OrderRepository orderRepository;
    private final PaymentDetailsRepository paymentRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ICreateRazorOrder createRazorOrder;

    @Override
    public OrderRecord createOrder(PlaceOrderRequestDto placeOrderRequestDto) throws RazorPayException {


        Long cartId = placeOrderRequestDto.getCartId();

        DeliveryAddresses addresses = deliveryAddressRepository.findById(placeOrderRequestDto.getDeliveryAddress())
                .orElseThrow(() -> new NoResourceFoundException("No Address found"));

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoResourceFoundException("No cart"));

        OrderRecord newOrder = new OrderRecord();


        newOrder.setOrderType(OrderType.ONLINE);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setCart(cart);
        newOrder.setDeliveryAddress(addresses);
        newOrder.setCustomerId(cart.getUserId());
        newOrder.setRestId(cart.getRestId());
        newOrder.setTotalAmount(cart.getTotalAmount());



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

            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentStatus(PaymentStatus.CREATED);
            payment.setModeOfPayment("ONLINE");
            payment.setOrderRecord(savedOrder);
            payment.setCartId(cartId);
            payment.setTotalAmount(cart.getTotalAmount());
            payment.setRazorpayOrderId(new JSONObject(razorpayOrderResponse).getString("id"));


            // Save the payment
            PaymentDetails savedPayment = paymentRepository.save(payment);

            log.info("Payment info at online order {} " , savedPayment);

            savedOrder.setPaymentDetails(savedPayment);

            // Update the OrderRecord with payment information
            OrderRecord save = orderRepository.save(savedOrder);

            log.info("Final order : {} " , save);

            return save;

        }  catch (Exception e) {
            throw new RazorPayException ("Razor is down currently !");
        }

    }

}
