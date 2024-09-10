package com.quick_bites.controller.restaurant_controller.search;


import com.quick_bites.dto.dish_dto.SingleDishResponseDto;
import com.quick_bites.services.restaurant_service.IGetSingleDish;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
public class GetSingleDishController {

    private final IGetSingleDish singleDish;

    @GetMapping("/dish")
    public ResponseEntity<SingleDishResponseDto> getSingleDishMethod(@RequestParam Long dishId) {

        SingleDishResponseDto dish = singleDish.getDish(dishId);

        return ResponseEntity.status(HttpStatus.OK).body(dish);

    }
}
