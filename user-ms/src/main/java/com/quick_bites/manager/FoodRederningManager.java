package com.quick_bites.manager;

import com.quick_bites.dto.Food;

import java.util.List;

public interface FoodRederningManager {

        List<Food> getFoodsByName(String name);

        List<Food> getFoodsByCategory(String name);

        List<Food> getFoodsByRestaurantName(String name);

        List<Food> getFoodsByReview();

        List<Food> getFoodsByDistance(String currentLocation);

        List<Food> getFoodsByPrice();

        List<Food> getFoodsByFoodType();

}
