package com.quick_bites.events;


import com.quick_bites.service.managers.order_manager.notification_manager.OrderProcessingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@AllArgsConstructor
public class OrderAcknowledgmentListener {

    private final OrderProcessingService orderProcessingService;

    @Bean
    public Consumer<OrderAcknowledgment> orderAcknowledged() {

        return acknowledgment -> {
            log.info("Acknowledgment received: {} ", acknowledgment);
            Long orderId = acknowledgment.getOrderId();
            orderProcessingService.processOrderAcknowledgment(orderId);
        };

    }

}
