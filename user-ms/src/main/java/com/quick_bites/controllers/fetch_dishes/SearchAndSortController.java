package com.quick_bites.controllers.fetch_dishes;


import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.webclient.DishSortingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

/**
 * @implNote = this controller fetching dishes with help of WEB CLIENT
 */



@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class SearchAndSortController {

    private final DishSortingService sortingService;

    @GetMapping("/web-client/search")
    public ResponseEntity<Flux<ResponseDishDto>>  getFilteredAndSortedDishes(
            @RequestParam("query") String query,
            @RequestParam(value = "minPrice", required = false , defaultValue = "0") Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "minDistance", required = false , defaultValue = "0") Double minDistance,
            @RequestParam(value = "maxDistance", required = false , defaultValue = "5") Double maxDistance,
            @RequestParam(value = "userLatitude", required = false) Double userLatitude,
            @RequestParam(value = "userLongitude", required = false) Double userLongitude,
            @RequestParam(value = "dishType", required = false) String dishType,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "ascending", required = false) boolean ascending
    ) {
        Flux<ResponseDishDto> dishes = sortingService
                .getFilteredAndSortedDishes(query, minPrice, maxPrice, minRating, minDistance,
                        maxDistance, userLatitude, userLongitude, dishType, sortBy, ascending);

        return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }
}
