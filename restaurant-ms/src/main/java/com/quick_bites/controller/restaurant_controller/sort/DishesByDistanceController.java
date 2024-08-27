package com.quick_bites.controller.restaurant_controller.sort;

import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.Dish;
import com.quick_bites.location_service.DistanceService;
import com.quick_bites.mapper.DishMapper;
import com.quick_bites.repository.dish_repo.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@AllArgsConstructor
public class DishesByDistanceController {

    private final DistanceService distanceService;
    private final DishRepository dishRepository;

    @GetMapping("/by-distance")
    public List<ResponseDishDto> getDishesByDistance(
            @RequestParam Double userLatitude,
            @RequestParam Double userLongitude,
            @RequestParam(required = false, defaultValue = "0") Double minDistance,
            @RequestParam(required = false, defaultValue = "5") Double maxDistance) {

        // Fetch all dishes
        List<Dish> dishes = dishRepository.findAll();


        // Sort dishes by distance
        List<Dish> sortedDishes = distanceService.sortDishesByDistance(dishes, userLatitude, userLongitude);


        // Apply distance filtering
        if (minDistance != null || maxDistance != null) {
            sortedDishes = sortedDishes.stream()
                    .filter(dish -> {
                        double distance = distanceService.calculateDistance(
                                userLatitude, userLongitude,
                                dish.getRestaurant().getLocation().getLatitude(),
                                dish.getRestaurant().getLocation().getLongitude());
                        return (minDistance == null || distance >= minDistance) &&
                                (maxDistance == null || distance <= maxDistance);
                    })
                    .toList();
        }

        return sortedDishes.stream().map(DishMapper::mapToDto).toList();
    }
}
