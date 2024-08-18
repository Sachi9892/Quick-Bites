package com.quick_bites.repository.dish_repo;

import com.quick_bites.entity.Category;
import com.quick_bites.entity.Dish;
import com.quick_bites.entity.DishType;
import com.quick_bites.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {


    //Will be used when restaurant tries to add new dish ( prevent adding of same dish )
    @Query("SELECT COUNT(d) > 0 FROM Dish d WHERE d.dishName = :dishName AND d.restaurant = :restaurant AND d.category = :category")
    Boolean existsByDishNameAndRestaurantAndCategory(
            @Param("dishName") String dishName,
            @Param("restaurant") Restaurant restaurant,
            @Param("category") Category category
    );


    //Find dish by name
    @Query("SELECT d FROM Dish d WHERE d.dishName = :dishName")
    Optional<Dish> findByName(@Param("dishName") String dishName);



    Page<Dish> findAllByDishName(String dishName , Pageable pageable);


    //Fetch dishes by dish type
    @Query("SELECT d FROM Dish d WHERE d.dishType = :dishType")
    Page<Dish> findAllByDishType(@Param("dishType") DishType dishType , Pageable pageable);



    //Fetch dishes by rating
    List<Dish> findAllByDishReviewsRatingGreaterThanEqual(double rating, Pageable pageable);


    //Fetch dishes by price
    List<Dish> findAllByPriceBetween(double minPrice, double maxPrice);


}
