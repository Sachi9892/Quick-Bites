package com.quick_bites.events;


import com.quick_bites.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Slf4j
public class OrderPlacedEvent {

    private final StreamBridge streamBridge;

    @Bean
    public Consumer<Message<OrderDetails>> listenOrderPlacedEvent() {

        return message -> {

            //Received the message
            OrderDetails orderDetails = message.getPayload();
            log.info("Order received in restaurant-ms service:  {} " , orderDetails);

            // Send acknowledgment
            OrderAcknowledgment acknowledgment = new OrderAcknowledgment
                    (orderDetails.getEventId() , orderDetails.getOrderId(), "restaurant-ms", "received");

            boolean send = streamBridge.send("listenOrderPlacedEvent-out-0", MessageBuilder.withPayload(acknowledgment).build());

            log.info("Acknowledgment Sent ? , {} " , send);

        };
    }

}
