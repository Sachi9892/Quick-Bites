package com.quick_bites.controller.restaurant_controller.sort;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.DishType;
import com.quick_bites.services.dishservice_public.IDishesByDishTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("restaurant/dishes")
public class AllDishesByTypeController {

    private final IDishesByDishTypeService typeService;

    @GetMapping("/dish-type")
    public ResponseEntity<List<ResponseDishDto>> dishesByDishType(@RequestParam DishType dishType) {

        List<ResponseDishDto> dishes = typeService.dishesByDishType(dishType);

        return ResponseEntity.status(HttpStatus.OK).body(dishes);

    }


}
