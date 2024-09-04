package com.quick_bites.controllers.cart_controller;


import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.service.managers.order_manager.cart_manager.RemoveFromCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
public class RemoveFromCartController {

    private final RemoveFromCart removeFromCart;

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeDishFromCart(@RequestBody RemoveFromCartDto fromCartDto) {

        Cart updatedCart = removeFromCart.removeFromCart(fromCartDto);
        return ResponseEntity.ok(updatedCart);

    }
}
