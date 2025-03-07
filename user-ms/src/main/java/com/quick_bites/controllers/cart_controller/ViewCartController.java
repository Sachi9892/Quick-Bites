package com.quick_bites.controllers.cart_controller;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.cart_manager.IViewCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
@CrossOrigin("*")
public class ViewCartController {

    private final IViewCart viewCartService;
    private final JwtUtils jwtUtil;

    @GetMapping("/view")
    public ResponseEntity<Cart> viewCartMethod( @RequestHeader("Authorization") String token) {

        Long userId = jwtUtil.extractUserId(token.replace("Bearer ", "")); // Extract userId from JWT
        Cart cart = viewCartService.viewCart(userId, CartStatus.ACTIVE);
        return ResponseEntity.ok(cart);

    }

}
