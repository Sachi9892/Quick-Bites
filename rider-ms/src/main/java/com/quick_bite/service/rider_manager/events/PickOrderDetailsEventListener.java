package com.quick_bite.service.rider_manager.events;


import com.quick_bite.dto.order_dto.PickOrderDetailsDto;
import com.quick_bite.dto.order_dto.PickUpOrderFullDetails;
import com.quick_bite.service.rider_manager.rider_assignment.AssignOrderToRiderService;
import com.quick_bite.service.rider_manager.rider_assignment.NotifyRiderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Slf4j
public class PickOrderDetailsEventListener {

    private final AssignOrderToRiderService assignOrderToRiderService;
    private final NotifyRiderService riderNotificationService;

    @Bean
    public Consumer<Message<PickOrderDetailsDto>> processPickOrderDetails() {
        return pickOrderDetailsDto -> {
            try {
                PickOrderDetailsDto orderDetails = pickOrderDetailsDto.getPayload();

                if (orderDetails.getOrderId() == null) {
                    log.error("Invalid pick order details: {}", pickOrderDetailsDto);
                    return;
                }

                log.info("Assigning Rider for Order ID: {}", orderDetails.getOrderId());

                // Assign the order to a rider
                PickUpOrderFullDetails fullDetails = assignOrderToRiderService.assignOrderToRider(orderDetails);

                if (fullDetails == null) {
                    log.warn("No rider available for Order ID: {}", orderDetails.getOrderId());
                    return;
                }

                log.info("Successfully assigned order to rider. Details: {}", fullDetails);

                // Notify the rider via the RiderNotificationService
                riderNotificationService.notifyRider(fullDetails);

            } catch (Exception e) {
                log.error("Error processing pick order details: {}", pickOrderDetailsDto, e);
            }
        };

    }

}
