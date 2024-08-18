package com.quick_bites.services.dish_service.impl;

import com.quick_bites.dto.dish_dto.AddDishDto;
import com.quick_bites.entity.Category;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.ResourceAlreadyPresent;
import com.quick_bites.repository.category_repo.CategoryRepository;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;

import com.quick_bites.exception.ResourceNotFoundException;
import com.quick_bites.services.dish_service.protected_services.AddDishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class IAddDishService implements AddDishService {

    private final RestaurantRepository restaurantRepo;
    private final CategoryRepository categoryRepo;
    private final DishRepository dishRepo;


    @Override
    @Transactional
    public String addDish(int id , AddDishDto addDishDto) {


        //Extract restaurant first
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new
                        ResourceNotFoundException("No restaurant found with id : " + id));


        // Handle category: fetch by name or create a new one
        Category category = categoryRepo.
                findByCategoryNameAndRestaurant(addDishDto.getCategory(), restaurant).orElse(null);

        if (category == null) {

            Category newCategory = new Category();
            newCategory.setCategoryName(addDishDto.getCategory());
            newCategory.setDescription(addDishDto.getDescription());
            newCategory.setRestaurant(restaurant);
            category = categoryRepo.save(newCategory);

        }

        //Check if dish is already present
        boolean isPresent = dishRepo
                .existsByDishNameAndRestaurantAndCategory(addDishDto.getDishName(), restaurant, category);

        if (isPresent) {
            throw new ResourceAlreadyPresent("Dish is already present : " + addDishDto.getDishName());
        }


        //Create and save dish
        Dish dish = new Dish();

        dish.setDishName(addDishDto.getDishName());
        dish.setCategory(category);
        dish.setRestaurant(restaurant);
        dish.setDescription(addDishDto.getDescription());
        dish.setPrice(addDishDto.getPrice());
        dish.setDishPic(addDishDto.getDishPic());
        dish.setDishType(addDishDto.getDishType());

        dishRepo.save(dish);
        log.info("Dish '{}' added successfully with ID: {}", dish.getDishName(), dish.getDishId());

        return "Dish added";

    }


}
