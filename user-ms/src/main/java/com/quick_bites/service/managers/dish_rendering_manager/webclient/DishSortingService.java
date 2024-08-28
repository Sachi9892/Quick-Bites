package com.quick_bites.service.managers.dish_rendering_manager.webclient;

import com.quick_bites.dto.dishdto.ResponseDishDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Optional;


@Service
public class DishSortingService {

    private final WebClient webClient;

    public DishSortingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://RESTAURANT-MS").build();
    }


    public Flux<ResponseDishDto> getFilteredAndSortedDishes(String query, Double minPrice, Double maxPrice,
                                                            Double minRating, Double minDistance, Double maxDistance,
                                                            Double userLatitude, Double userLongitude,
                                                            String dishType, String sortBy, boolean ascending) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/dishes/sort")
                        .queryParam("query", query)
                        .queryParamIfPresent("minPrice", Optional.ofNullable(minPrice))
                        .queryParamIfPresent("maxPrice", Optional.ofNullable(maxPrice))
                        .queryParamIfPresent("minRating", Optional.ofNullable(minRating))
                        .queryParamIfPresent("minDistance", Optional.ofNullable(minDistance))
                        .queryParamIfPresent("maxDistance", Optional.ofNullable(maxDistance))
                        .queryParamIfPresent("userLatitude", Optional.ofNullable(userLatitude))
                        .queryParamIfPresent("userLongitude", Optional.ofNullable(userLongitude))
                        .queryParamIfPresent("dishType", Optional.ofNullable(dishType))
                        .queryParamIfPresent("sortBy", Optional.ofNullable(sortBy))
                        .queryParam("ascending", ascending)
                        .build())
                .retrieve()
                .bodyToFlux(ResponseDishDto.class);
    }

}
