package com.quick_bites.service.managers.order_manager.events;


import com.quick_bites.dto.confirm_order_dto.PickOrderDetailsDto;
import com.quick_bites.exceptions.KafkaMessageFailedError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import static com.quick_bites.constants.AppConstants.PICK_ORDER_DETAILS;

@Service
@AllArgsConstructor
@Slf4j
public class PickUpOrderDetailsPublisher {

    private final StreamBridge streamBridge;

    public void publishPickOrderDetails(PickOrderDetailsDto pickOrderDetailsDto) {

        try {
            log.info("Publishing PickOrderDetails to Kafka: {}", pickOrderDetailsDto);
            boolean success = streamBridge.send("publishPickOrderDetails-out-0", pickOrderDetailsDto);
            if (!success) {
                throw new KafkaMessageFailedError("Failed to send message to Kafka.");
            }
        } catch (Exception e) {
            log.error("Error publishing PickOrderDetails to Kafka: {}", pickOrderDetailsDto, e);
        }
    }
}
