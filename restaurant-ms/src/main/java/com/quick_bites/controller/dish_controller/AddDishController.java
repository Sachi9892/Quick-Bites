package com.quick_bites.controller.dish_controller;

import com.quick_bites.dto.dish_dto.AddDishDto;
import com.quick_bites.services.dish_service.protected_services.AddDishService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/add")
@AllArgsConstructor
public class AddDishController {

    private final AddDishService addDishService;

    @PostMapping("/dish/{id}")
    public ResponseEntity<String> addDish(@PathVariable Long id, @ModelAttribute AddDishDto dishDto) {
        addDishService.addDish(id, dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dish added successfully!");
    }
}
