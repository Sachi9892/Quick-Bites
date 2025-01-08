package com.quick_bite.service.rider_manager.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick_bite.dto.rider_dto.RiderLocationUpdate;
import com.quick_bite.service.rider_manager.rider_assignment.FindNearestRiderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Objects;

/**
 * RiderWebSocketHandler handles WebSocket connections for riders.
 * It manages rider sessions and updates their locations.
 */

@Configuration
@Slf4j
@AllArgsConstructor
public class RiderWebSocketHandler extends TextWebSocketHandler {

    // In-memory map to store rider details
    private final FindNearestRiderService riderService;
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON processing

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String riderId = getRiderIdFromSession(session);
        if (riderId != null) {
            riderService.addRiderSession(riderId, session);
            log.info("Rider {} connected", riderId);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String riderId = getRiderIdFromSession(session);
        if (riderId == null) {
            log.warn("Rider ID is null, ignoring message");
            return;
        }

        String payload = message.getPayload();
        log.info("Received message from Rider {}: {}", riderId, payload);

        try {
            RiderLocationUpdate locationUpdate = objectMapper.readValue(payload, RiderLocationUpdate.class);
            riderService.updateRiderLocation(riderId, locationUpdate);
        } catch (Exception e) {
            log.error("Error processing message from Rider {}: {}", riderId, payload, e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {

        String riderId = getRiderIdFromSession(session);

        if (riderId != null) {
            riderService.removeRiderSession(riderId);
            log.info("Rider {} disconnected", riderId);
        }

    }

    private String getRiderIdFromSession(WebSocketSession session) {
        try {
            return Objects.requireNonNull(session.getUri()).getQuery().split("=")[1];
        } catch (Exception e) {
            log.error("Failed to extract riderId from session", e);
            return null;
        }
    }


}
