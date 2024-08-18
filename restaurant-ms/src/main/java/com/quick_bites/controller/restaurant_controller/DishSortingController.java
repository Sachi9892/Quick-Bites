package com.quick_bites.controller.restaurant_controller;


import com.quick_bites.dto.dish_dto.ResponseDishDto;
import com.quick_bites.entity.User;
import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.repository.user_repo.UserRepository;
import com.quick_bites.services.dishservice_public.DishSortingAndFilterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/dish")
@AllArgsConstructor
public class DishSortingController {

    private final DishSortingAndFilterService filterService;
    private final UserRepository userRepository;

    @GetMapping("/sort")
    public ResponseEntity<List<ResponseDishDto>> sortDishes(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "restaurant", required = false) String restaurant,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "minDistance", required = false) Double minDistance,
            @RequestParam(value = "maxDistance", required = false) Double maxDistance,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "ascending", defaultValue = "true") boolean ascending,
            @RequestParam(value = "userId") int userId)
    {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user"));

        if (user == null || user.getLocation().getLatitude() == 0 || user.getLocation().getLongitude() == 0) {
            throw new IllegalArgumentException("User location data is missing.");
        }

        List<ResponseDishDto> filteredAndSortedDishes = filterService.getFilteredAndSortedDishes(
                category,
                name,
                restaurant,
                minPrice,
                maxPrice,
                minRating,
                minDistance,
                maxDistance,
                user.getLocation().getLatitude(),
                user.getLocation().getLongitude(),
                null,
                user,
                sortBy,
                ascending);

        return ResponseEntity.status(HttpStatus.OK).body(filteredAndSortedDishes);
    }

}
