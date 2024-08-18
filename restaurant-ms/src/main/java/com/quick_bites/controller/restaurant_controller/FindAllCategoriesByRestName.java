package com.quick_bites.controller.restaurant_controller;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.services.restaurant_service.FindAllCategories;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class FindAllCategoriesByRestName {

    private final FindAllCategories findAllCategories;

    @GetMapping("/categories")
    public ResponseEntity<Set<ResponseCategoryDto>> findCategories(@RequestParam String name) {

        Set<ResponseCategoryDto> categories = findAllCategories.allCategories(name);

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }


}
