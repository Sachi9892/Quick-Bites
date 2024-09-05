package com.quick_bites.controllers.order_controller;


import com.quick_bites.service.managers.order_manager.cart_manager.IFindUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/find")
@AllArgsConstructor
public class FndUserController {

    private final IFindUser findUserService;

    @GetMapping("/user")
    public ResponseEntity<String> getUser(@RequestParam Long cartId) {

        Long user = findUserService.findUser(cartId);

        return ResponseEntity.status(HttpStatus.OK).body("User is " + user);
    }


}
