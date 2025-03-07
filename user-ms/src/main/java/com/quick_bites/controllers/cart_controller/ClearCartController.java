package com.quick_bites.controllers.cart_controller;


import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.cart_manager.IClearCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
@CrossOrigin("*")
public class ClearCartController {

    private final IClearCart clearCartService;
    private final JwtUtils jwtUtil;

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCartMethod(@RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.extractUserId(token.replace("Bearer ", "")); // Extract userId from JWT
        clearCartService.clearCart(userId);
        return ResponseEntity.noContent().build();

    }

}
