package com.quick_bite.service.locationiq_services;

import com.quick_bite.constants.AppConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class GeoCodeService {

    private final WebClient webClient;

    public GeoCodeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.locationiq.com/v1/").build();
    }

    public Mono<String> getGeocode(String address) {
        return webClient.get()
                .uri("search.php?key={key}&q={address}&format=json", AppConstants.API_KEY, address)
                .retrieve()
                .bodyToMono(String.class);
    }

}
