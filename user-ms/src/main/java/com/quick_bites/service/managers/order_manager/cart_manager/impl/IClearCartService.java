package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.entity.Cart;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.ClearCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IClearCartService implements ClearCart {

    private final CartRepository cartRepository;

    @Override
    public Cart clearCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user id: " + userId));

        cart.getCartItems().clear();
        cart.setTotalDishes(0);
        cart.setTotalAmount(0.0);

        cartRepository.delete(cart);

        return new Cart();

    }

}
