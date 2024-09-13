package com.quick_bites.service.managers.order_manager.order_service;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.DeliveryAddresses;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.NoAddressFoundException;
import com.quick_bites.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderBaseService {


    protected final OrderRepository orderRepository;
    protected final PaymentDetailsRepository paymentRepository;
    protected final DeliveryAddressRepository deliveryAddressRepository;
    protected final UserRepository userRepository;
    protected final CartRepository cartRepository;


    public OrderBaseService(OrderRepository orderRepository,
                            PaymentDetailsRepository paymentRepository,
                            DeliveryAddressRepository deliveryAddressRepository,
                            UserRepository userRepository,
                            CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    protected OrderRecord createOrderBase(PlaceOrderRequestDto placeOrderRequestDto) {

        Long cartId = placeOrderRequestDto.getCartId();

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("No cart"));

        DeliveryAddresses addresses = deliveryAddressRepository.findById(placeOrderRequestDto.getDeliveryAddress())
                .orElseThrow(() -> new NoAddressFoundException(" No address found "));

        Long userId = cart.getUserId();

        OrderRecord newOrder = new OrderRecord();
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setCart(cart);
        newOrder.setDeliveryAddress(addresses);
        newOrder.setCustomerId(userId);
        newOrder.setRestId(cart.getRestId());
        newOrder.setTotalAmount(cart.getTotalAmount());
        return newOrder;

    }
}
