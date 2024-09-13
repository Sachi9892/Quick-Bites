package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.DishNotFoundException;
import com.quick_bites.repository.CartItemRepository;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.IRemoveFromCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RemoveFromCartServiceImpl implements IRemoveFromCart {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @Override
    public Cart removeFromCart(RemoveFromCartDto removeFromCartDto) {

        // Find the cart for the user
        Cart cart = cartRepository.findByUserIdAndStatus(removeFromCartDto.getUserId(), CartStatus.ACTIVE)
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
            // Remove the item if quantity is 1 or less
            cart.getCartItems().remove(existingItem);
            cartItemRepository.deleteById(existingItem.getId());
        }

        // If the cart is empty after the removal, set restId to null
        if (cart.getCartItems().isEmpty()) {
            cart.setRestId(null);
        }

        // Update the total dishes and total amount in the cart
        cart.setTotalDishes(cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum());

        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);

    }

}
