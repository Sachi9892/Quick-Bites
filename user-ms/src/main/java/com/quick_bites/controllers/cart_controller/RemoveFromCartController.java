package com.quick_bites.controllers.cart_controller;


import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.cart_manager.IRemoveFromCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
@CrossOrigin("*")
public class RemoveFromCartController {

    private final IRemoveFromCart removeFromCart;
    private final JwtUtils jwtUtil;

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeDishFromCart(
            @RequestHeader("Authorization") String token,
            @RequestBody RemoveFromCartDto fromCartDto) {

        Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
        fromCartDto.setUserId(userId);
        Cart updatedCart = removeFromCart.removeFromCart(fromCartDto);
        return ResponseEntity.ok(updatedCart);

    }
}
