package com.quick_bites.location_service;

import com.quick_bites.constants.AppConstants;
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

    public LatLng getCoordinates(String address) {
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
                return response.getResults().get(0).getGeometry().getLocation();
            }
            throw new RuntimeException("Unable to get coordinates for address: " + address);
        } catch (Exception e) {
            logger.error("Error occurred while fetching coordinates", e);
            throw new RuntimeException("Error occurred while fetching coordinates", e);
        }
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
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
