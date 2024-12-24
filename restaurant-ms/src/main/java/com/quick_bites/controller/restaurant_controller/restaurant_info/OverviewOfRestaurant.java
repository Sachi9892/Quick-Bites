package com.quick_bites.controller.restaurant_controller.restaurant_info;

import com.quick_bites.dto.restaurant_dto.RestaurantOverViewDto;
import com.quick_bites.services.restaurant_service.IRestaurantOverview;
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
@RequestMapping("/restaurant")
@AllArgsConstructor
public class OverviewOfRestaurant {

    private final IRestaurantOverview overview;

    @GetMapping("/overview")
    public ResponseEntity<RestaurantOverViewDto> getOverview(@RequestParam String name ) {
        RestaurantOverViewDto overView = overview.getOverView(name );
        return ResponseEntity.status(HttpStatus.OK).body(overView);
    }

}
