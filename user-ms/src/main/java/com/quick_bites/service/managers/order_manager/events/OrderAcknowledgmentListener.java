package com.quick_bites.service.managers.order_manager.events;


import com.quick_bites.service.managers.order_manager.events.dto.OrderAcknowledgment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@AllArgsConstructor
public class OrderAcknowledgmentListener {

    private final PostAcknowledgmentProcessor postAcknowledgmentProcessor;

    @Bean
    public Consumer<OrderAcknowledgment> processAcknowledgment() {
        return acknowledgment -> {
            try {
                log.info("Processing acknowledgment: {}", acknowledgment);
                postAcknowledgmentProcessor.processPostAcknowledgment(acknowledgment);
            } catch (Exception e) {
                log.error("Error processing acknowledgment: {}", acknowledgment, e);
                throw e;
            }
        };
    }

}
