package com.quick_bites.controllers.order_controller;



import com.quick_bites.dto.orderdto.OrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderStatus;
import com.quick_bites.entity.OrderType;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.order_service.ICreateOrderService;
import com.quick_bites.service.managers.order_manager.order_service.impl.OrderServiceFactory;
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
    private final OrderServiceFactory orderServiceFactory;
    private final OrderRepository orderRepository;



    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/order/place-order")
    public ResponseEntity<String> createOrderMethod(@RequestBody OrderRequestDto orderRequest) {

        try {

            ICreateOrderService orderService = orderServiceFactory.getOrderService(orderRequest.getOrderType().name());

            OrderRecord order = orderService.createOrder(orderRequest);

            if (orderRequest.getOrderType() == OrderType.ONLINE) {

                createRazorOrder.createRazorpayOrder(orderRequest.getCartId());

                orderRepository.save(order);

                return ResponseEntity.ok("Online payment order created successfully");

            } else if (orderRequest.getOrderType() == OrderType.COD) {


                return ResponseEntity.ok("Order placed with Cash on Delivery.");

            }

        } catch (Exception e) {

            log.info("Inside catch bloc {} ");
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error placing order");

        }

        log.info("outside catch bloc" );
        return ResponseEntity.status(500).body("Not in method body placing order");
    }


}
