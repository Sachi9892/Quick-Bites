package com.quick_bites.service.managers.order_manager.payment_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.IOnlinePaymentService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class OnlinePaymentServiceImpl implements IOnlinePaymentService {

    private final CartRepository cartRepository;

    @Override
    public String initiateOnlinePayment(Long cartId , Long userId)  {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart update" + cartId));

        log.info("Razor created for cart : {}  ", cartId);

        cart.setStatus(CartStatus.ORDERED);
        cartRepository.save(cart);

        return "Payment initiated for cart: " + cartId;
    }

}
