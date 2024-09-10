package com.quick_bites.controller.restaurant_controller;

import com.quick_bites.dto.restaurant_dto.AddRestaurantDto;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.services.restaurant_service.IAddRestaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class AddRestaurantController {

    private final IAddRestaurant addRestaurantService;

    @PostMapping("/add")
    public ResponseEntity<Restaurant> addRest(@RequestBody AddRestaurantDto addRestaurantDto) {
        Restaurant restaurant = addRestaurantService.addRestaurant(addRestaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
    }

}
