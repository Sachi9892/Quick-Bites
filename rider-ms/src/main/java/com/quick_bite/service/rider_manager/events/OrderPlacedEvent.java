package com.quick_bite.service.rider_manager.events;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
@Slf4j
public class OrderPlacedEvent {

    private final StreamBridge streamBridge;

    @Bean
    public Function<OrderDetails, OrderAcknowledgment> processOrderPlacedEvent() {
        return orderDetails -> {
            // Process order details and create acknowledgment
            OrderAcknowledgment acknowledgment = new OrderAcknowledgment(
                    orderDetails.getEventId(),
                    orderDetails.getOrderId(),
                    "rider-ms",
                    "received");

            log.info("Received ack from user-ms for event : {} " , orderDetails.getOrderId());

            streamBridge.send("processOrderPlacedEvent-out-0", MessageBuilder.withPayload(acknowledgment).build());

            log.info("From Rider-MS Ack Sent For Placed Order Event ");

            return acknowledgment; // Assuming a return value is needed

        };
    }

}
