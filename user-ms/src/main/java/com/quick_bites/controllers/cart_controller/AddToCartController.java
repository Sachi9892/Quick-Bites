package com.quick_bites.controllers.cart_controller;


import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.cart_manager.IAddToCart;
import com.quick_bites.entity.Cart;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
@CrossOrigin("*")
public class AddToCartController {

    private final IAddToCart addToCartService;
    private final JwtUtils jwtUtils;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCartMethod(
            @RequestHeader("Authorization") String token,
            @RequestBody AddToCartDto addToCartDto) {

        Long userId = jwtUtils.extractUserId(token.replace("Bearer ", " ")); // Extract userId from JWT
        addToCartDto.setUserId(userId); // Set userId in the DTO
        Cart cart = addToCartService.addDishesToCart(addToCartDto);
        return ResponseEntity.ok(cart);

    }
}
