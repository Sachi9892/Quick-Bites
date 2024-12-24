package com.quick_bites.controller.restaurant_controller.restaurant_info;

import com.quick_bites.dto.category_dto.ResponseCategoryDto;
import com.quick_bites.services.restaurant_service.IFindAllCategories;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/restaurant")
@AllArgsConstructor
public class FindAllCategoriesByRestName {

    private final IFindAllCategories findAllCategories;

    @GetMapping("/categories")
    public ResponseEntity<List<ResponseCategoryDto>> findCategories(@RequestParam String name) {
        List<ResponseCategoryDto> categories = findAllCategories.allCategories(name);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

}
