package com.quick_bite.service.rider_manager.rider_assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick_bite.dto.order_dto.PickUpOrderFullDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class NotifyRiderService {

    private final FindNearestRiderService findNearestRiderService;

    public void notifyRider(PickUpOrderFullDetails fullDetails) {

        WebSocketSession session = findNearestRiderService.getSession(fullDetails.getRiderId());

        if (session != null && session.isOpen()) {
            try {

                Map<String, Object> message = new HashMap<>();
                message.put("type", "ORDER");
                message.put("payload", fullDetails);

                ObjectMapper objectMapper = new ObjectMapper();

                String messageJson = objectMapper.writeValueAsString(message);
                log.info("Message to be sent: {}", messageJson);
                log.info("Type of message {} " , messageJson.getClass().getName());

                session.sendMessage(new TextMessage(messageJson));

                log.info("Notified rider {} for Order ID: {}", fullDetails.getRiderId(), fullDetails.getOrderId());

            } catch (Exception e) {
                log.error("Error notifying rider {} for Order ID: {}", fullDetails.getRiderId(), fullDetails.getOrderId(), e);
            }
        } else {
            log.warn("WebSocket session not available for Rider ID: {}", fullDetails.getRiderId());
        }
    }


}
