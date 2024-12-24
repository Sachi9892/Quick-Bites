package com.quick_bites.controller.restaurant_controller.search;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.restaurant_service.IFindAllDishesByDishNameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("restaurant/dishes")
@AllArgsConstructor
public class AllDishesByNameController {

    private final IFindAllDishesByDishNameService dishNameService;

    @GetMapping("/dish-name")
    public ResponseEntity<List<ResponseDishDto>> allDishes(@RequestParam String name ) {

        List<ResponseDishDto> responseDishDtos = dishNameService.allDishesByDishName(name );

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);
    }


}
