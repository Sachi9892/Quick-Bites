package com.quick_bites.service.managers.dish_rendering_manager.search;


import com.quick_bites.dto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.webclient.DishesByCategoryWebClient;
import com.quick_bites.service.managers.dish_rendering_manager.webclient.DishesByNameWebClient;
import com.quick_bites.service.managers.dish_rendering_manager.webclient.DishesByRestaurantWebClient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SearchDishesApi {

    private final DishesByCategoryWebClient categoryNameWebClient;
    private final DishesByNameWebClient dishNameWebClient;
    private final DishesByRestaurantWebClient restaurantNameWebClient;

    public Mono<ResponseEntity<Page<ResponseDishDto>>> searchDishes(String query, int page, int size) {

        return dishNameWebClient.getDishesByDishName(query, page, size)
                .map(dishes -> ResponseEntity.status(HttpStatus.OK).body(dishes))

                .onErrorResume(e -> categoryNameWebClient.getDishesByCategoryName(query, page, size)
                        .map(dishes -> ResponseEntity.status(HttpStatus.OK).body(dishes))

                        .onErrorResume(ex -> restaurantNameWebClient.getDishesByRestaurantName(query, page, size)
                                .map(dishes -> ResponseEntity.status(HttpStatus.OK).body(dishes))

                                .defaultIfEmpty(ResponseEntity.notFound().build())
                        )
                );
    }


}
