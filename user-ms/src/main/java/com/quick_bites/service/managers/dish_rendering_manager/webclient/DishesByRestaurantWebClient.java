package com.quick_bites.service.managers.dish_rendering_manager.webclient;


import com.quick_bites.dto.ResponseDishDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DishesByRestaurantWebClient {

    private final WebClient webClient;

    public Mono<Page<ResponseDishDto>> getDishesByRestaurantName(String name, int page, int size) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/dishes/restaurant-name")
                        .queryParam("name", name)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }
}
