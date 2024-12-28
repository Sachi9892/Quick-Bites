package com.quick_bites.service.managers.order_manager.events;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@AllArgsConstructor
public class OrderAcknowledgmentListener {

    private final AcknowledgmentProcessor acknowledgmentProcessor;

    @Bean
    public Consumer<OrderAcknowledgment> orderAcknowledged() {

        return acknowledgment -> {
            log.info("Acknowledgment received: {}", acknowledgment);

            try {
                acknowledgmentProcessor.processAcknowledgment(acknowledgment);
            } catch (Exception e) {
                log.error("Error processing acknowledgment for orderId: {}", acknowledgment.getOrderId(), e);
            }
        };
    }

}
