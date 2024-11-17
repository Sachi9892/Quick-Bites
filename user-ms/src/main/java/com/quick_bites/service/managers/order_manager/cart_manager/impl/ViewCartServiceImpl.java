package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.IViewCart;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ViewCartServiceImpl implements IViewCart {

    private final CartRepository cartRepository;

    @Override
    public Cart viewCart(Long userId , CartStatus status) {
        return cartRepository.findByUserIdAndStatus(userId , CartStatus.ACTIVE).orElseThrow(() -> new CartNotFoundException("No cart for user :" + userId));
    }
}
