package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.ViewCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IViewCartService implements ViewCart {

    private final CartRepository cartRepository;

    @Override
    public Cart viewCart(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }
}
