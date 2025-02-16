package com.quick_bites.service.managers.order_manager.payment_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.quick_bites.service.managers.order_manager.payment_manager.IOnlinePaymentService;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OnlinePaymentServiceImpl implements IOnlinePaymentService {

    private final CartRepository cartRepository;
    private final ICreateRazorOrder createRazorOrder;

    @Override
    public Cart initiateOnlinePayment(Long cartId) throws RazorpayException {

        createRazorOrder.createRazorpayOrder(cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("No cart update" + cartId));

        cart.setStatus(CartStatus.ORDERED);

        return cartRepository.save(cart);
    }
}
