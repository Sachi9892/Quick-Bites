package com.quick_bites.events;


import com.quick_bites.service.managers.order_manager.notification_manager.OrderProcessingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Service
@Slf4j
@AllArgsConstructor
public class OrderAcknowledgmentListener {

    private final OrderProcessingService orderProcessingService;
    private final Map<Long, Boolean> processedOrders = new ConcurrentHashMap<>();

    @Bean
    public Consumer<OrderAcknowledgment> orderAcknowledged() {

        return acknowledgment -> {
            log.info("Acknowledgment received: {}", acknowledgment);
            Long orderId = acknowledgment.getOrderId();

            // Ensure the entry is added only if not already present
            boolean isFirstAcknowledgment = processedOrders.putIfAbsent(orderId, true) == null;

            if (isFirstAcknowledgment) {
                log.info("Processing acknowledgment for the first time for orderId: {}", orderId);
                orderProcessingService.processOrderAcknowledgment(orderId);
                log.info("Message Processing");
            } else {
                log.info("Acknowledgment for orderId: {} has already been processed.", orderId);
            }

        };

    }

}
