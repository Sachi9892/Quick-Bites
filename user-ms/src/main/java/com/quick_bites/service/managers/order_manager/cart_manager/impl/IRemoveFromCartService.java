package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.RemoveFromCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class IRemoveFromCartService implements RemoveFromCart {

    private final CartRepository cartRepository;


    @Override
    public Cart removeFromCart(RemoveFromCartDto removeFromCartDto) {

        Cart cart = cartRepository.findByUserId(removeFromCartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getDishId().equals(removeFromCartDto.getDishId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found"));


        if (existingItem.getQuantity() > 1) {
            existingItem.setQuantity(existingItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(existingItem);
        }

        // Update total dishes and total amount
        cart.setTotalDishes(cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum());

        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);

    }

}
