package com.quick_bites.controller.restaurant_controller.search;


import com.quick_bites.services.restaurant_service.IFindDishPriceAndRestIdByDishId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dish")
@AllArgsConstructor
public class PriceByDishIdController {

    private final IFindDishPriceAndRestIdByDishId priceById;

    @GetMapping("/price")
    public ResponseEntity<Double> findPrice(@RequestParam Long dishId) {

        Double price = priceById.getPrice(dishId);

        return ResponseEntity.status(HttpStatus.OK).body(price);

    }

}
