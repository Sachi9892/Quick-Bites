package com.quick_bites.controller.restaurant_controller;

import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.services.restaurant_service.RestaurantOverview;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class OverviewOfRestaurant {

    private final RestaurantOverview overview;

    @GetMapping("/overview")
    public ResponseEntity<RestaurantOverViewDto> getOverview(
            @RequestParam String name ,
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "10") int size) {

        Pageable res = PageRequest.of(page, size);

        RestaurantOverViewDto overView = overview.getOverView(name , res);

        return ResponseEntity.status(HttpStatus.OK).body(overView);

    }

}
