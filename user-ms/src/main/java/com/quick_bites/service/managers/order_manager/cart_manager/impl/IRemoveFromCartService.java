package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.DishNotFoundException;
import com.quick_bites.repository.CartItemRepository;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.RemoveFromCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;


@Service
@AllArgsConstructor
public class IRemoveFromCartService implements RemoveFromCart {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @Override
    public Cart removeFromCart(RemoveFromCartDto removeFromCartDto) {

        // Find the cart for the user
        Cart cart = cartRepository.findByUserId(removeFromCartDto.getUserId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + removeFromCartDto.getUserId()));


        // Find the cart item based on dishId
        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getDishId().equals(removeFromCartDto.getDishId()))
                .findFirst()
                .orElseThrow(() -> new DishNotFoundException("Dish not found in the cart for dish ID: " + removeFromCartDto.getDishId()));


        // Decrease quantity or remove the item from the cart
        if (existingItem.getQuantity() > 1) {
            existingItem.setQuantity(existingItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(existingItem);
        }


        // Update the total dishes and total amount in the cart
        cart.setTotalDishes(cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum());
        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);

    }

}
