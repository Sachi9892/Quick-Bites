package com.quick_bites.controllers.order_controller;


import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class PlaceNewOrder {

    private final IPlaceOrderFactory placeOrderFactory;

    @PostMapping("/place")
    public OrderRecord placeOrder(@RequestBody PlaceOrderRequestDto requestDto) throws Exception {

        return placeOrderFactory.placeOrder(requestDto);

    }

}
