package com.quick_bites.controller.dish_controller;

import com.quick_bites.dto.dish_dto.AddDishDto;
import com.quick_bites.services.dish_service.protected_services.AddDishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
@AllArgsConstructor
public class AddDishController {

    private final AddDishService addDishService;

    @PostMapping("/dish/{id}")
    public ResponseEntity<String> addDish(@PathVariable int id , @RequestBody AddDishDto dishDto) {

        addDishService.addDish(id , dishDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Dish added successfully !");

    }

}
