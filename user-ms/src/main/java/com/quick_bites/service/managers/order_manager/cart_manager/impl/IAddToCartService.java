package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.managers.order_manager.cart_manager.AddToCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class IAddToCartService implements AddToCart {

    private final CartRepository cartRepository;
    private final RestaurantClient restaurantClient;

    @Override
    public Cart addDishesToCart(AddToCartDto addToCartDto) {

        Long userId = addToCartDto.getUserId();
        Long dishId = addToCartDto.getDishId();

        Double price = restaurantClient.findPrice(dishId).getBody();

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(userId, new ArrayList<>(), 0, LocalDateTime.now()));


        List<CartItem> existingItems = cart.getCartItems().stream()
                .filter(item -> item.getDishId().equals(dishId)).toList();


        if (!existingItems.isEmpty()) {
            CartItem firstItem = existingItems.get(0);
            int totalQuantity = existingItems.stream().mapToInt(CartItem::getQuantity).sum();
            firstItem.setQuantity(totalQuantity + 1);
            firstItem.setPrice(price);
        } else {
             CartItem newItem = CartItem.builder()
                    .dishId(dishId)
                    .userId(userId)
                    .quantity(1)
                    .price(price)
                    .cart(cart)
                    .build();
            cart.getCartItems().add(newItem);
        }


        //Total dishes
        cart.setTotalDishes(cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum());


        //Total Amount
        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);
    }


}
