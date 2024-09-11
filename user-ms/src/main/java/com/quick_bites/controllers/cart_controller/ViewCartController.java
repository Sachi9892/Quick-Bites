package com.quick_bites.controllers.cart_controller;

import com.quick_bites.entity.Cart;
import com.quick_bites.service.managers.order_manager.cart_manager.IViewCart;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
public class ViewCartController {

    private final IViewCart viewCart;

    @GetMapping("/view")
    public ResponseEntity<Cart> viewCartMethod(@RequestParam Long userId) {

        Cart cart = viewCart.viewCart(userId);
        return ResponseEntity.ok(cart);

    }

}
