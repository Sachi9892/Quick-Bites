package com.quick_bites.controllers.order_controller.order_now;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.dto.paymentdto.OrderResponse;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderType;
import com.quick_bites.jwt.JwtUtils;
import com.quick_bites.service.managers.order_manager.events.OrderPlaceEvent;
import com.quick_bites.exceptions.PlaceOrderException;
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
public class OrderNowController {

    private final PlaceOrderServiceFactory orderNowServiceFactory;
    private final IOnlinePaymentService onlinePaymentService;
    private final OrderPlaceEvent orderPlaceEvent;
    private final JwtUtils jwtUtils;

    @PostMapping("/order-now")
    public ResponseEntity<?> createOrderMethod(

            @RequestHeader("Authorization") String token,
            @RequestBody PlaceOrderRequestDto orderRequest) {

        try {

            Long userId = jwtUtils.extractUserId(token);

            IPlaceOrderFactory onlineOrderNow = orderNowServiceFactory.placeOrder(orderRequest.getOrderType());
            OrderRecord order = onlineOrderNow.placeOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.ONLINE) {

                String razorpayOrderId = onlinePaymentService.initiateOnlinePayment(orderRequest.getCartId() , userId);

                return ResponseEntity.ok(
                        new OrderResponse("Online payment initialized", razorpayOrderId)
                );

            } else {
                //Send Event to the Restaurant-Ms and Rider-Ms
                orderPlaceEvent.sendOrderPlacedEvent(order);
                return  ResponseEntity.ok("Order placed with Cash On Delivery.");
            }

        } catch (Exception e) {
            throw new PlaceOrderException("Failed to place scheduled order: " + e.getMessage());
        }

    }

}
