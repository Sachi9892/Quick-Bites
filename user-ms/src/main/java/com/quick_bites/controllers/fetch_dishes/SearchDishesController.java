package com.quick_bites.controllers.fetch_dishes;


import com.quick_bites.dto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.search.SearchDishesApi;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class SearchDishesController {

    private final SearchDishesApi searchDishesApi;

    @GetMapping("/dishes/search")
    public Mono<ResponseEntity<Page<ResponseDishDto>>> searchDishes(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return searchDishesApi.searchDishes(query, page, size);

    }

}
