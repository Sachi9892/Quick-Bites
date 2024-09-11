package com.quick_bites.controllers.cart_controller;


import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.service.managers.order_manager.cart_manager.IAddToCart;
import com.quick_bites.entity.Cart;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
public class AddToCartController {

    private final IAddToCart addToCartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCartMethod(@RequestBody AddToCartDto addToCartDto) {

        Cart updatedCart = addToCartService.addDishesToCart(addToCartDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCart);

    }
}
