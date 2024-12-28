package com.quick_bites.controller.restaurant_controller.order_controller;


import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.services.restaurant_service.ISendPickUpOrderRestaurantDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class PickUpOrderRestaurantDetailsController {

    private final ISendPickUpOrderRestaurantDetails restaurantDetails;

    @GetMapping("/pickup/details")
    public ResponseEntity<RestaurantPickOrderDetailsDto> sendRestDetails(@RequestParam Long restId) {
        RestaurantPickOrderDetailsDto restaurantPickOrderDetailsDto = restaurantDetails.sendDetails(restId);
        return ResponseEntity.ok(restaurantPickOrderDetailsDto);
    }


}
