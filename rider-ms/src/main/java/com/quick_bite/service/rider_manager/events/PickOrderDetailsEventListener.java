package com.quick_bite.service.rider_manager.events;


import com.quick_bite.dto.PickOrderDetailsDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Slf4j
public class PickOrderDetailsEventListener {


    /**
     * Consumer bean to listen to `pick_order_details` Kafka topic and process the message.
     *
     * @return Consumer that processes PickOrderDetailsDto messages.
     */

    @Bean
    public Consumer<PickOrderDetailsDto> listenPickOrderDetails() {

        return pickOrderDetailsDto -> {

            log.info("Received message from topic `pick_order_details`: {}", pickOrderDetailsDto);

            try {
                // Validate incoming data
                if (pickOrderDetailsDto == null || pickOrderDetailsDto.getOrderId() ==  null ) {
                    log.error("Invalid PickOrderDetailsDto received: {}", pickOrderDetailsDto);
                    return;
                }

                // Process the pick order details (e.g., store in database, update rider tasks, etc.)
                log.info("Processing pick order details for Order ID: {}", pickOrderDetailsDto.getOrderId());

                log.info("Successfully processed pick order details: {}", pickOrderDetailsDto);

            } catch (Exception e) {
                log.error("Error processing message from topic `pick_order_details`: {}", pickOrderDetailsDto, e);

            }
        };
    }


}
