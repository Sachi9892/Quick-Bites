package com.quick_bites.controller.restaurant_controller;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.services.dishservice_public.FindAllDishesByPrice;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/rest/dish")
@AllArgsConstructor
public class AllDishesByPriceController {

    private final FindAllDishesByPrice dishesByPrice;

    @GetMapping("/price")
    public ResponseEntity<List<ResponseDishDto>> allDishesByPrice(
            @RequestParam double minPrice ,
            @RequestParam double maxPrice ) {


        List<ResponseDishDto> responseDishDtos = dishesByPrice.dishesByPrice(minPrice, maxPrice);

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);
    }

}
