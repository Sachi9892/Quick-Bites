package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.IClearCart;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class ClearCartServiceImpl implements IClearCart {

    private final CartRepository cartRepository;

    @Override
    @CachePut(value = "user_cart" , key = "{#userId}")
    public Cart clearCart(Long userId) {

        Cart cart = cartRepository.findByUserIdAndStatus(userId , CartStatus.ACTIVE)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user id: " + userId));

        cart.getCartItems().clear();
        cart.setTotalDishes(0);
        cart.setTotalAmount(0.0);
        cart.setRestId(null);


        Cart clearedCart = cartRepository.save(cart);
        log.info("Clear cart : {} " , clearedCart);

        return clearedCart;


    }

}
