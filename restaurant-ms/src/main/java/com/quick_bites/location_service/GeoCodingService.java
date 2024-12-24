package com.quick_bites.location_service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick_bites.dto.location_dto.LocationDto; // Import your LocationDto
import com.quick_bites.exception.CoordinatesNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
public class GeoCodingService {

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.path}")
    private String baseUrl;

    private final WebClient webClient;

    public GeoCodingService(WebClient.Builder webClientBuilder, @Value("${google.api.path}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public LocationDto getCoordinates(String address) {

        try {
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            String url = String.format("/geocode/json?address=%s&key=%s", encodedAddress, apiKey);

            log.info("Request URL: {}", baseUrl + url);

            String rawResponse = webClient.get()
                    .uri(URI.create(baseUrl + url))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Raw API Response: {}", rawResponse);

            GeocodingResponse response = new ObjectMapper().readValue(rawResponse, GeocodingResponse.class);

            if (response == null || !"OK".equalsIgnoreCase(response.getStatus())) {
                log.error("Geocoding API returned error status: {}", response != null ? response.getStatus() : "null");
                throw new CoordinatesNotFoundException("Unable to get coordinates for address: " + address);
            }

            if (response.getResults() == null || response.getResults().isEmpty()) {
                log.error("No results found for address: {}", address);
                throw new CoordinatesNotFoundException("No results found for address: " + address);
            }

            LatLng location = response.getResults().get(0).getGeometry().getLocation();
            return new LocationDto(location.getLat(), location.getLng(), address);

        } catch (Exception e) {
            log.error("Error occurred while fetching coordinates", e);
            throw new CoordinatesNotFoundException("Error occurred while fetching coordinates ");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GeocodingResponse {
        private String status;
        private List<Result> results;

        @Getter
        @Setter
        public static class Result {
            private Geometry geometry;

            @Getter
            @Setter
            public static class Geometry {
                private LatLng location;
            }
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LatLng {
        private double lat;
        private double lng;
    }
}
