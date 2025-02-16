package com.quick_bites.repository.restaurant_repo;

import com.quick_bites.entity.Dish;
import com.quick_bites.entity.Restaurant;
import com.quick_bites.entity.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


    //Will find restaurant by given restaurant name
    @Query("SELECT r FROM Restaurant r WHERE r.restaurantName = :restaurantName")
    Optional<Restaurant> findByRestaurantName(@Param("restaurantName") String restaurantName);


    // Will give a list of dishes by given CATEGORY name
    @Query("SELECT d FROM Dish d WHERE LOWER(d.category.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    List<Dish> findAllDishesByCategoryName(@Param("categoryName") String categoryName);



    // Will give a list of dishes by given RESTAURANT name
    @Query("SELECT d FROM Dish d LEFT JOIN FETCH d.dishReviews WHERE LOWER(d.restaurant.restaurantName) LIKE LOWER(CONCAT('%', :restaurantName, '%'))")
    List<Dish> findAllDishesByRestaurantName(@Param("restaurantName") String restaurantName);



    // Will give a list of dishes by DISH name
    @Query("SELECT d FROM Dish d WHERE LOWER(d.dishName) LIKE LOWER(CONCAT('%', :dishName, '%'))")
    List<Dish> findAllDishesByDishName(@Param("dishName") String dishName);



    //Select reviews based on restaurant name
    @Query("SELECT r.restReviews FROM Restaurant r WHERE r.restaurantName = :restaurantName")
    List<RestaurantReview> findAllReviewsByRestaurantName(@Param("restaurantName") String restaurantName);




}
