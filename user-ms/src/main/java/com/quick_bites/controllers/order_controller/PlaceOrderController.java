package com.quick_bites.controllers.order_controller;



import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.*;
import com.quick_bites.exceptions.CartNotFoundException;
import com.quick_bites.exceptions.PlaceOrderException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import com.quick_bites.service.managers.order_manager.order_service.order_now.impl.OrderNowServiceFactory;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@Slf4j
public class PlaceOrderController {

    private final ICreateRazorOrder createRazorOrder;
    private final OrderNowServiceFactory orderNowServiceFactory;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/order/order-now")
    public ResponseEntity<String> createOrderMethod(@RequestBody PlaceOrderRequestDto orderRequest) {

        try {

            ICreateOrderNowService orderService = orderNowServiceFactory.getOrderNowService(orderRequest.getOrderType().name());

            OrderRecord order = orderService.createOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.ONLINE) {
                createRazorOrder.createRazorpayOrder(orderRequest.getCartId());

                Cart cart = cartRepository.findById(orderRequest.getCartId()).orElseThrow(() -> new CartNotFoundException("No cart update" + orderRequest.getCartId()));

                cart.setStatus(CartStatus.ORDERED);
                cartRepository.save(cart);
                orderRepository.save(order);
                return ResponseEntity.ok("Online payment order created successfully");

            } else if (orderRequest.getOrderType() == OrderType.COD) {

                return ResponseEntity.ok("Order placed with Cash on Delivery.");

            }

        } catch (Exception e) {

            throw new PlaceOrderException("Failed to placed order ");

        }

        throw new PlaceOrderException("Failed to placed order ");
    }


}
