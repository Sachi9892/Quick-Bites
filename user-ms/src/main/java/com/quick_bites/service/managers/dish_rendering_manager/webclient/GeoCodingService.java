package com.quick_bites.service.managers.dish_rendering_manager.webclient;


import com.quick_bites.dto.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeoCodingService {

    private final WebClient webClient;

    public GeoCodingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://RESTAURANT-MS").build();
    }

    public Mono<LocationDto> getCoordinates(String address) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/geocoding/coordinates")
                        .queryParam("address", address)
                        .build())
                .retrieve()
                .bodyToMono(LocationDto.class);
    }

}
