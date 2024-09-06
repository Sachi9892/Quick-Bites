package com.quick_bites.repository.dish_repo;

import com.quick_bites.dto.restaurant_dto.RestIdAndDishPrice;
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

public interface DishRepository extends JpaRepository<Dish, Long> {


    //Will be used when restaurant tries to add new dish ( prevent listing of same dish )
    @Query("SELECT COUNT(d) > 0 FROM Dish d WHERE d.dishName = :dishName AND d.restaurant = :restaurant AND d.category = :category")
    Boolean existsByDishNameAndRestaurantAndCategory(
            @Param("dishName") String dishName,
            @Param("restaurant") Restaurant restaurant,
            @Param("category") Category category
    );


    //Find dish by name
    @Query("SELECT d FROM Dish d WHERE d.dishName = :dishName")
    Optional<Dish> findByName(@Param("dishName") String dishName);


    //Fetch dishes by dish type
    @Query("SELECT d FROM Dish d WHERE d.dishType = :dishType")
    List<Dish> findAllByDishType(@Param("dishType") DishType dishType);


    //Fetch dishes by rating
    List<Dish> findAllByDishReviewsRatingGreaterThanEqual(Double rating);


    //Fetch dishes by price
    List<Dish> findAllByPriceBetween(Double minPrice, Double maxPrice);


    //Find price by dish id
    @Query("SELECT d.price FROM Dish d WHERE d.dishId = :dishId")
    Double findPriceByDishId(@Param("dishId") Long dishId);

}
