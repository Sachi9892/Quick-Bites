package com.quick_bites.controllers.order_controller.schedule_order;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.dto.paymentdto.OrderResponse;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderType;
import com.quick_bites.exceptions.PlaceOrderException;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.events.OrderPlaceEvent;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.PlaceOrderServiceFactory;
import com.quick_bites.service.managers.order_manager.payment_manager.IOnlinePaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/checkout")
@AllArgsConstructor
@CrossOrigin("*")
public class ScheduleOrderController {

    private final PlaceOrderServiceFactory scheduleOrderServiceFactory;
    private final IOnlinePaymentService onlinePaymentService;
    private final JwtUtils jwtUtils;
    private final OrderPlaceEvent orderPlaceEvent;


    @PostMapping("/schedule-order")
    public ResponseEntity<?> createOrderMethod(
            @RequestHeader("Authorization") String token,
            @RequestBody PlaceOrderRequestDto orderRequest) {

        try {

            Long userId = jwtUtils.extractUserId(token);

            IPlaceOrderFactory onlineOrderNow = scheduleOrderServiceFactory.placeOrder(orderRequest.getOrderType());

            OrderRecord order = onlineOrderNow.placeOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.SCHEDULE_ONLINE) {

                String razorpayOrderId = onlinePaymentService.initiateOnlinePayment(orderRequest.getCartId() , userId);

                return ResponseEntity.ok(
                        new OrderResponse("Online payment initialized", razorpayOrderId)
                );

            } else {
                orderPlaceEvent.sendOrderPlacedEvent(order);
                return ResponseEntity.ok("Order placed with Cash on Delivery.");
            }

        } catch (Exception e) {
            throw new PlaceOrderException("Failed to place scheduled order: " + e.getMessage());
        }

    }

}
