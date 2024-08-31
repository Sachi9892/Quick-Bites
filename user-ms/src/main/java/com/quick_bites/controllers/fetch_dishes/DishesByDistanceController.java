package com.quick_bites.controllers.fetch_dishes;


import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.service.managers.dish_rendering_manager.search.DishesByDistance;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class DishesByDistanceController {

    private final DishesByDistance dishesByDistance;

    @GetMapping("/by-distance")
    public ResponseEntity<List<ResponseDishDto>> byDistance(
            @RequestParam Long userId ,
            @RequestParam(required = false) Double minDistance ,
            @RequestParam(required = false) Double maxDistance) {

        List<ResponseDishDto> dishes = dishesByDistance.getDishesByDistance(userId , minDistance , maxDistance);

        return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }
}
