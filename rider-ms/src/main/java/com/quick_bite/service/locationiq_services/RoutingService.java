package com.quick_bite.service.locationiq_services;

import com.quick_bite.constants.AppConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class RoutingService {

    private final WebClient webClient;

    public RoutingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.locationiq.com/v1/").build();
    }

    public Mono<String> getRoute(String startCoords, String endCoords) {

        return webClient.get()
                .uri("directions/driving/{startCoords};{endCoords}?key={key}&overview=false",
                        startCoords, endCoords, AppConstants.API_KEY)
                .retrieve()
                .bodyToMono(String.class);

    }
}
