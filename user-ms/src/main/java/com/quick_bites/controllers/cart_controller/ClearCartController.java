package com.quick_bites.controllers.cart_controller;


import com.quick_bites.service.managers.order_manager.cart_manager.IClearCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
public class ClearCartController {

    private final IClearCart clearCart;

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCartMethod(@RequestParam Long userId) {

        clearCart.clearCart(userId);
        return ResponseEntity.noContent().build();

    }

}
