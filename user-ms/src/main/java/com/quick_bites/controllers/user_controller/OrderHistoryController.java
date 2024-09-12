package com.quick_bites.controllers.user_controller;


import com.quick_bites.dto.orderdto.OrderDetailsDto;
import com.quick_bites.service.user_profile.IFetchOrderHistory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class OrderHistoryController {

    private final IFetchOrderHistory orderHistory;

    @GetMapping("/profile/orders")
    public ResponseEntity<List<OrderDetailsDto>> getUserOrders(@RequestParam Long userId) {

        List<OrderDetailsDto> orderDetails = orderHistory.getUserOrders(userId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);

    }
}
