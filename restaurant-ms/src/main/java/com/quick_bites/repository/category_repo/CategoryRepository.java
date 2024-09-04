package com.quick_bites.repository.category_repo;

import com.quick_bites.entity.Category;
import com.quick_bites.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category , Long> {

    //Checks if category is there
    @Query("SELECT c FROM Category c WHERE c.categoryName = :name")
    Optional<Category> findByCategoryName(@Param("name") String name);


    //Will fetch set categories of a SINGLE  restaurant
    @Query("SELECT c FROM Category c JOIN c.restaurant r WHERE r.restaurantName = :restName")
    Set<Category> findAllByRestaurantName(@Param("restName") String restName);



    //Find a single Category of particular restaurant ( will be used when restaurant try to add nw dish )
    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName AND c.restaurant = :restaurant")
    Optional<Category> findByCategoryNameAndRestaurant(@Param("categoryName") String categoryName, @Param("restaurant") Restaurant restaurant);


}
