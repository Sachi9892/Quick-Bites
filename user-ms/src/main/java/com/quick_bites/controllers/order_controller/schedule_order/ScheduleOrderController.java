package com.quick_bites.controllers.order_controller.schedule_order;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderType;
import com.quick_bites.exceptions.PlaceOrderException;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.PlaceOrderServiceFactory;
import com.quick_bites.service.managers.order_manager.payment_manager.IOnlinePaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/checkout")
@AllArgsConstructor
public class ScheduleOrderController {

    private final PlaceOrderServiceFactory scheduleOrderServiceFactory;
    private final IOnlinePaymentService onlinePaymentService;

    @PostMapping("/schedule-order")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createOrderMethod(@RequestBody PlaceOrderRequestDto orderRequest) {

        try {

            IPlaceOrderFactory onlineOrderNow = scheduleOrderServiceFactory.placeOrder(orderRequest.getOrderType());

            OrderRecord order = onlineOrderNow.placeOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.SCHEDULE_ONLINE) {
                Cart updatedCart = onlinePaymentService.initiateOnlinePayment(orderRequest.getCartId());
                order.setCart(updatedCart);
                return ResponseEntity.ok("Online payment order created successfully");
            } else {
                return ResponseEntity.ok("Order placed with Cash on Delivery.");
            }

        } catch (Exception e) {
            throw new PlaceOrderException("Failed to place scheduled order: " + e.getMessage());
        }

    }

}
