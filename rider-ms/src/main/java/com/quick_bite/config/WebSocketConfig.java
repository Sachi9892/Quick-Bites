package com.quick_bite.config;


import com.quick_bite.service.rider_manager.websocket.RiderWebSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final RiderWebSocketHandler riderWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Register WebSocket handler for rider connections
        registry.addHandler(riderWebSocketHandler, "/rider-location")
                .setAllowedOrigins("*"); // Allowing all origins for simplicity

    }
}
