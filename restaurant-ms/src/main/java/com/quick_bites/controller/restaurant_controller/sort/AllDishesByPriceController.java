package com.quick_bites.controller.restaurant_controller.sort;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.dishservice_public.IFindAllDishesByPrice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/dishes")
@AllArgsConstructor
public class AllDishesByPriceController {

    private final IFindAllDishesByPrice byPrice;

    @GetMapping("/price-range")
    public ResponseEntity<List<ResponseDishDto>> dishesInPrice(@RequestParam Double minPrice  , @RequestParam Double maxPrice) {

        List<ResponseDishDto> dishes = byPrice.dishesByPrice(minPrice, maxPrice);
        return ResponseEntity.status(HttpStatus.OK).body(dishes);

    }

}
