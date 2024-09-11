package com.quick_bites.controllers.order_controller.order_details;


import com.quick_bites.dto.orderdto.OrderDetailsDto;
import com.quick_bites.service.managers.order_manager.order_details.impl.CustomerOrderDetailsServiceImpl;
import com.quick_bites.service.user_profile.UserOrderHistory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class OrderDetailController {

    private final CustomerOrderDetailsServiceImpl orderDetailsService;

    @GetMapping("/all-orders")
    public ResponseEntity<UserOrderHistory> getOrdersByUserId(@RequestParam Long userId) {

        UserOrderHistory userOrderHistory = orderDetailsService.getUserOrderHistory(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userOrderHistory);

    }


}
