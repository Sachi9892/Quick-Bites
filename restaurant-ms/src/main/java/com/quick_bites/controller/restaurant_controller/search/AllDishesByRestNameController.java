package com.quick_bites.controller.restaurant_controller.search;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.restaurant_service.IFindAllDishesByRestName;
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
@RequestMapping("/dishes")
@AllArgsConstructor
public class AllDishesByRestNameController {

    private final IFindAllDishesByRestName allDishesByRestName;

    @GetMapping("/restaurant-name")
    public ResponseEntity<List<ResponseDishDto>> allDishes(@RequestParam String name) {
        List<ResponseDishDto> dishes = allDishesByRestName.findAllDishesByRestaurantName(name);
        return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }
}
