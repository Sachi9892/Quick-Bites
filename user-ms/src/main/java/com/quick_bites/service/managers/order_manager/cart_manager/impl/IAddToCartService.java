package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.RestIdAndDishPrice;
import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.exceptions.MultipleRestaurantOrderException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.managers.order_manager.cart_manager.AddToCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class IAddToCartService implements AddToCart {

    private final CartRepository cartRepository;
    private final RestaurantClient restaurantClient;

    @Override
    public Cart addDishesToCart(AddToCartDto addToCartDto) {

        ResponseEntity<Double> price1 = restaurantClient.findPrice(addToCartDto.getDishId());


        Double price = price1.getBody();


        //User id and dish id we will send
        Long userId = addToCartDto.getUserId();
        Long dishId = addToCartDto.getDishId();
        Long restId = addToCartDto.getRestId();


        // Fetch the user's cart or create a new one
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(userId , restId , new ArrayList<>() , 0 , 0.0 , LocalDateTime.now()));


        if (cart.getRestId() != null && !cart.getRestId().equals(restId)) {
            throw new MultipleRestaurantOrderException("You cannot add dishes from multiple restaurants. Please place a separate order.");
        }


        // Find if the dish already exists in the cart
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
                    .restId(restId)
                    .quantity(1)
                    .price(price)
                    .cart(cart)
                    .build();
            cart.getCartItems().add(newItem);
        }

        // Total dishes and amount calculations
        cart.setTotalDishes(cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum());

        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);

    }

}
