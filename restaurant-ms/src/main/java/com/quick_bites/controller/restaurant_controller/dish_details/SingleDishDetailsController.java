package com.quick_bites.controller.restaurant_controller.dish_details;

import com.quick_bites.dto.dish_dto.SingleDishResponseDto;
import com.quick_bites.services.dishservice_public.ISingleDishDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
@RequestMapping("/restaurant")
public class SingleDishDetailsController {

    private final ISingleDishDetailsService dishDetailsService;

    @GetMapping("/dish")
    ResponseEntity<SingleDishResponseDto> getSingleDishMethod(@RequestParam Long dishId) {
        SingleDishResponseDto singleDishResponseDto = dishDetailsService.dishDetails(dishId);
        return ResponseEntity.ok(singleDishResponseDto);
    }

}
