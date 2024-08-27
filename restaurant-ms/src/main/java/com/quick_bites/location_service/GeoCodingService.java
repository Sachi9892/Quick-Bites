package com.quick_bites.location_service;

import com.quick_bites.dto.location_dto.LocationDto; // Import your LocationDto
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class GeoCodingService {

    private static final Logger logger = LoggerFactory.getLogger(GeoCodingService.class);

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.path}")
    private String baseUrl;

    private final WebClient webClient;

    public GeoCodingService(WebClient.Builder webClientBuilder,
                            @Value("${google.api.path}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public LocationDto getCoordinates(String address) {
        try {
            // Encode the address
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);

            // Construct the URL
            String url = String.format("/geocode/json?address=%s&key=%s", encodedAddress, apiKey);

            // Log the request URL
            logger.info("Request URL: {}", baseUrl + url);

            // Make the request
            GeocodingResponse response = webClient.get()
                    .uri(URI.create(baseUrl + url))
                    .retrieve()
                    .bodyToMono(GeocodingResponse.class)
                    .block();

            // Log the response
            logger.info("API Response: {}", response);

            // Process the response
            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                LatLng location = response.getResults().get(0).getGeometry().getLocation();
                return new LocationDto(location.getLat(), location.getLng(), address);
            }
            throw new RuntimeException("Unable to get coordinates for address: " + address);
        } catch (Exception e) {
            logger.error("Error occurred while fetching coordinates", e);
            throw new RuntimeException("Error occurred while fetching coordinates", e);
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GeocodingResponse {
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
}
