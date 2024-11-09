package com.quick_bite.service.locationiq_services;

import com.fasterxml.jackson.databind.JsonNode;
import com.quick_bite.constants.AppConstants;
import com.quick_bite.dto.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class GeoCodeService {

    private final WebClient webClient;

    public GeoCodeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.locationiq.com/v1/").build();
    }

    public LocationDto getGeocode(String address) {

        return webClient.get()
                .uri("search.php?key={key}&q={address}&format=json", AppConstants.API_KEY, address)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    // Assuming we take the first result in the response array
                    JsonNode firstResult = response.get(0);
                    double latitude = firstResult.get("lat").asDouble();
                    double longitude = firstResult.get("lon").asDouble();
                    return new LocationDto(latitude, longitude,address);
                })
                .block(); // Blocks and waits for the response

    }
}
