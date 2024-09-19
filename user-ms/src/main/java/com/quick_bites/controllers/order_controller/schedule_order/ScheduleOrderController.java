package com.quick_bites.controllers.order_controller.schedule_order;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderType;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.PlaceOrderException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.order_service.IPlaceOrderFactory;
import com.quick_bites.service.managers.order_manager.order_service.PlaceOrderServiceFactory;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
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


    private final ICreateRazorOrder createRazorOrder;
    private final PlaceOrderServiceFactory scheduleOrderServiceFactory;
    private final CartRepository cartRepository;

    @PostMapping("/schedule-order")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> createOrderMethod(@RequestBody PlaceOrderRequestDto orderRequest) {

        try {

            IPlaceOrderFactory onlineOrderNow = scheduleOrderServiceFactory.placeOrder(orderRequest.getOrderType());

            OrderRecord order = onlineOrderNow.placeOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.SCHEDULE_ONLINE) {

                createRazorOrder.createRazorpayOrder(orderRequest.getCartId());

                Cart cart = cartRepository.findById(orderRequest.getCartId()).orElseThrow(() -> new CartNotFoundException("No cart update" + orderRequest.getCartId()));

                cart.setStatus(CartStatus.ORDERED);

                Cart updatedCart = cartRepository.save(cart);
                order.setCart(updatedCart);

                return ResponseEntity.ok("Online payment order created successfully");

            } else if (orderRequest.getOrderType() == OrderType.SCHEDULE_COD) {
                return ResponseEntity.ok("Order placed with Cash on Delivery.");
            } else {
                return ResponseEntity.badRequest().body("Invalid order type");
            }

        } catch (Exception e) {
            throw new PlaceOrderException("Failed to place scheduled order: " + e.getMessage());
        }

    }

}
