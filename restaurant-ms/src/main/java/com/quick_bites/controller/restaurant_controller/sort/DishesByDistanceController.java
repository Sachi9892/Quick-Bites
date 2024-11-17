package com.quick_bites.controller.restaurant_controller.sort;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.location_service.IDishesByDistanceService;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@AllArgsConstructor
public class DishesByDistanceController {

    private final IDishesByDistanceService dishesByDistanceService;
    private final DishRepository dishRepository;

    @GetMapping(value = "/by-distance" , produces = "application/json")
    @Cacheable(value = "dishes_by_distance", key = "#userLatitude + '_' + #userLongitude + '_' + #minDistance + '_' + #maxDistance")

    public ResponseEntity<List<ResponseDishDto>> getDishesByDistance(
            @RequestParam Double userLatitude,
            @RequestParam Double userLongitude,
            @RequestParam(required = false, defaultValue = "0") Double minDistance,
            @RequestParam(required = false, defaultValue = "5") Double maxDistance) {

        //Get All dishes from dish repo
        List<Dish> allDishes = dishRepository.findAll();

        // Delegate the business logic to the service layer
        List<Dish> dishes = dishesByDistanceService.getDishesByDistance(allDishes , userLatitude, userLongitude, minDistance, maxDistance);

        List<ResponseDishDto> responseDishDtos = dishes.stream().map(DishMapper::mapToDto).toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseDishDtos);
    }
}
