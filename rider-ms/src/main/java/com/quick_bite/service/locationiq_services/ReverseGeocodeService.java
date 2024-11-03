package com.quick_bite.service.locationiq_services;

import com.quick_bite.constants.AppConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ReverseGeocodeService {

    private final WebClient webClient;

    public ReverseGeocodeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.locationiq.com/v1/").build();
    }

    public Mono<String> getReverseGeocode(double latitude, double longitude) {
        return webClient.get()
                .uri("reverse.php?key={key}&lat={lat}&lon={lon}&format=json", AppConstants.API_KEY, latitude, longitude)
                .retrieve()
                .bodyToMono(String.class);
    }
}
