package com.quick_bites.controller.restaurant_controller;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.services.dishservice_public.FindAllDishesByNameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class AllDishesByNameController {

    private final FindAllDishesByNameService byNameService;

    @GetMapping("/dishes")
    public ResponseEntity<Page<ResponseDishDto>> allDishes(
            @RequestParam String name ,
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest request = PageRequest.of(page , size);

        Page<ResponseDishDto> responseDishDtos = byNameService.allDishesByName(name, request);

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);

    }


}
