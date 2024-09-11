package com.quick_bites.service.managers.order_manager.cart_manager.impl;

import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.dto.dishdto.DishType;
import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.exceptions.DishNotFoundException;
import com.quick_bites.exceptions.MultipleRestaurantOrderException;
import com.quick_bites.repository.CartItemRepository;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.managers.order_manager.cart_manager.IAddToCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AddToCartServiceImpl implements IAddToCart {

    private final CartRepository cartRepository;
    private final RestaurantClient restaurantClient;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart addDishesToCart(AddToCartDto addToCartDto) {

        // Fetch dish details from the restaurant client
        ResponseEntity<SingleDishResponseDto> response = restaurantClient.getSingleDishMethod(addToCartDto.getDishId());

        SingleDishResponseDto dishDto = response.getBody();
        if (dishDto == null) {
            throw new DishNotFoundException("Dish not found " + addToCartDto.getDishId());
        }

        // Extract necessary data
        Long userId = addToCartDto.getUserId();
        Long dishId = addToCartDto.getDishId();


        Double price = dishDto.getPrice();
        Long restId = dishDto.getRestId();
        String dishName = dishDto.getDishName();
        DishType dishType = dishDto.getDishType();
        String dishPic = dishDto.getDishPic();

        // Fetch or create the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(userId, restId, new ArrayList<>(), 0, 0.0, LocalDateTime.now()));

        // Check if the cart already has items from a different restaurant
        if (cart.getRestId() != null && !cart.getRestId().equals(restId)) {
            throw new MultipleRestaurantOrderException("You cannot add dishes from multiple restaurants.");
        }


        // Save the cart if it's new before proceeding with items
        if (cart.getCartId() == null) {
            cart = cartRepository.save(cart);  // Persist the cart first
        }


        // Find existing items and update their quantities
        List<CartItem> existingItems = cart.getCartItems().stream()
                .filter(item -> item.getDishId().equals(dishId))
                .toList();

        if (!existingItems.isEmpty()) {
            CartItem firstItem = existingItems.get(0);
            firstItem.setQuantity(firstItem.getQuantity() + 1); // Update quantity
            firstItem.setPrice(price);
        } else {
            // Create new cart item
            CartItem newItem = CartItem.builder()
                    .dishId(dishId)
                    .userId(userId)
                    .restId(restId)
                    .quantity(1)
                    .price(price)
                    .dishPic(dishPic)
                    .dishType(dishType)
                    .dishName(dishName)
                    .cart(cart)  // Associate with cart
                    .build();

            cart.getCartItems().add(newItem); // Add item to cart

            cartItemRepository.save(newItem);
        }

        // Update total dishes and amount
        cart.setTotalDishes(cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum());

        cart.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum());

        return cartRepository.save(cart);
    }

}
