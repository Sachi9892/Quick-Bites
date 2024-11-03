package com.quick_bites.services.dish_service.impl;

import com.quick_bites.dto.dish_dto.AddDishDto;
import com.quick_bites.entity.Category;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.exception.DishAlreadyPresentException;
import com.quick_bites.exception.RestaurantNotFoundException;
import com.quick_bites.repository.category_repo.CategoryRepository;
import com.quick_bites.repository.dish_repo.DishRepository;
import com.quick_bites.repository.restaurant_repo.RestaurantRepository;
import com.quick_bites.services.dish_service.protected_services.AddDishService;
import com.quick_bites.services.dish_service.protected_services.ImageUploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@AllArgsConstructor
public class IAddDishService implements AddDishService {

    private final RestaurantRepository restaurantRepo;
    private final CategoryRepository categoryRepo;
    private final DishRepository dishRepo;
    private final ImageUploadService imageUploadService;

    @Override
    @Transactional
    public void addDish(Long id, AddDishDto addDishDto) {

        // Extract restaurant
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found with id: " + id));

        // Handle category: fetch by name or create a new one
        Category category = categoryRepo.findByCategoryNameAndRestaurant(addDishDto.getCategory(), restaurant)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setCategoryName(addDishDto.getCategory());
                    newCategory.setDescription(addDishDto.getDescription());
                    newCategory.setRestaurant(restaurant);
                    return categoryRepo.save(newCategory);
                });

        // Check if the dish is already present
        if (dishRepo.existsByDishNameAndRestaurantAndCategory(addDishDto.getDishName(), restaurant, category)) {
            throw new DishAlreadyPresentException("Dish is already present: " + addDishDto.getDishName());
        }

        // Upload dish image
        String dishImageUrl = null;
        MultipartFile dishPic = addDishDto.getDishPic();

        if (dishPic != null && !dishPic.isEmpty()) {
            dishImageUrl = imageUploadService.uploadImage(dishPic, restaurant.getRestaurantName(), category.getCategoryName(), addDishDto.getDishName());
        }
        // Create and save the dish
        Dish dish = new Dish();
        dish.setDishName(addDishDto.getDishName());
        dish.setCategory(category);
        dish.setRestaurant(restaurant);
        dish.setDescription(addDishDto.getDescription());
        dish.setPrice(addDishDto.getPrice());
        dish.setDishPic(dishImageUrl);
        dish.setDishType(addDishDto.getDishType());

        dishRepo.save(dish);
        log.info("Dish '{}' added successfully with ID: {}", dish.getDishName(), dish.getDishId());

    }

}
