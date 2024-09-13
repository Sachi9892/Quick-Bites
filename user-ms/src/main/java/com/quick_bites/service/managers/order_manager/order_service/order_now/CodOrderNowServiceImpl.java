package com.quick_bites.service.managers.order_manager.order_service.order_now;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.*;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.OrderBaseService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Qualifier("codOrderNowService")
public class CodOrderNowServiceImpl  extends OrderBaseService implements IPlaceOrderFactory {

    public CodOrderNowServiceImpl(OrderRepository orderRepository, PaymentDetailsRepository paymentRepository, DeliveryAddressRepository deliveryAddressRepository, UserRepository userRepository, CartRepository cartRepository) {
        super(orderRepository, paymentRepository, deliveryAddressRepository, userRepository, cartRepository);
    }

    @Override
    public OrderRecord placeOrder(PlaceOrderRequestDto requestDto) throws RazorpayException {

        OrderRecord newOrder = createOrderBase(requestDto);
        newOrder.setOrderType(OrderType.COD);
        newOrder.setOrderStatus(OrderStatus.PLACED);

        // Save the OrderRecord first
        OrderRecord savedOrder = orderRepository.save(newOrder);

        PaymentDetails payment = new PaymentDetails();
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setModeOfPayment(PaymentMode.COD);
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
