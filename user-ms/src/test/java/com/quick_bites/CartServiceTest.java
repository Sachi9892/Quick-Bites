package com.quick_bites;



import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.cart_manager.AddToCart;
import com.quick_bites.service.managers.order_manager.cart_manager.impl.IAddToCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private IAddToCartService cartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testAddDishesToCart_NewItem() {
        // Arrange
        Long userId = 1L;
        Long dishId = 3L;
        Double price = 580.0;
        AddToCartDto addToCartDto = new AddToCartDto(dishId, userId, price);

        // Mocking the repository to return an empty cart
        Cart cart = new Cart(userId, new ArrayList<>(), 0, LocalDateTime.now());
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        Cart updatedCart = cartService.addDishesToCart(addToCartDto);

        // Assert
        assertNotNull(updatedCart);
        assertEquals(1, updatedCart.getCartItems().size());
        assertEquals(dishId, updatedCart.getCartItems().get(0).getDishId());
        assertEquals(1, updatedCart.getCartItems().get(0).getQuantity());
        assertEquals(price, updatedCart.getCartItems().get(0).getPrice());
        assertEquals(1, updatedCart.getTotalDishes());
        assertEquals(price, updatedCart.getTotalAmount());

        // Verify that the cart was saved
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
     void testAddDishesToCart_ExistingItem() {
        // Arrange
        Long userId = 1L;
        Long dishId = 3L;
        Double price = 580.0;
        AddToCartDto addToCartDto = new AddToCartDto(dishId, userId, price);

        // Existing cart item
        CartItem existingItem = CartItem.builder()
                .dishId(dishId)
                .quantity(1)
                .price(price)
                .build();

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(existingItem);

        Cart cart = new Cart(userId, cartItems, 1, LocalDateTime.now());
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        Cart updatedCart = cartService.addDishesToCart(addToCartDto);

        // Assert
        assertNotNull(updatedCart);
        assertEquals(1, updatedCart.getCartItems().size());
        assertEquals(2, updatedCart.getCartItems().get(0).getQuantity());
        assertEquals(price, updatedCart.getTotalAmount());

        // Verify that the cart was saved
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
     void testAddDishesToCart_NullPrice_ShouldThrowException() {
        // Arrange
        Long userId = 1L;
        Long dishId = 3L;
        Double price = null; // Price is null
        AddToCartDto addToCartDto = new AddToCartDto(dishId, userId, price);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addDishesToCart(addToCartDto);
        });

        // Verify that the cart was not saved
        verify(cartRepository, never()).save(any(Cart.class));
    }

}
