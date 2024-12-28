package com.quick_bites.service.managers.dish_rendering_manager.feign_client;


import com.quick_bites.dto.location_dto.CalculateDistanceDto;
import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.dto.dishdto.ResponseDishDto;
import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import com.quick_bites.dto.reviewdto.GiveReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "RESTAURANT-MS")
public interface RestaurantClient {

    @GetMapping("restaurant/dishes/search")
    List<ResponseDishDto> getFilteredAndSortedDishes(
            @RequestParam("query") String query,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "minDistance", required = false) Double minDistance,
            @RequestParam(value = "maxDistance", required = false) Double maxDistance,
            @RequestParam(value = "userLatitude", required = false) Double userLatitude,
            @RequestParam(value = "userLongitude", required = false) Double userLongitude,
            @RequestParam(value = "dishType", required = false) String dishType,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "ascending", required = false) boolean ascending
    );


    //For latitude and longitude
    @GetMapping("restaurant/api/geocoding/coordinates")
    LocationDto getCoordinates(@RequestParam("address") String address);


    //Review dish
    @PostMapping("restaurant/review/dish")
    String giveReview(@RequestBody GiveReviewDto reviewDto);


    //Review restaurant
    @PostMapping("restaurant/review/rest")
    String reviewRest(@RequestBody GiveReviewDto reviewDto);


    //Get dishes by distance
    @GetMapping("restaurant/dishes/by-distance")
    List<ResponseDishDto> getDishesByDistance(
            @RequestParam Double userLatitude,
            @RequestParam Double userLongitude,
            @RequestParam(required = false, defaultValue = "0") Double minDistance,
            @RequestParam(required = false, defaultValue = "5") Double maxDistance);


    //Get the price of dish
    @GetMapping("restaurant/dish/price")
    ResponseEntity<Double> findPrice(@RequestParam Long dishId);


    @GetMapping("restaurant/dish")
    ResponseEntity<SingleDishResponseDto> getSingleDishMethod(@RequestParam Long dishId);


    @GetMapping("restaurant/pickup/details")
    ResponseEntity<RestaurantPickOrderDetailsDto> sendRestDetails(@RequestParam Long restId);

    @GetMapping("restaurant/calculate/distance")
    ResponseEntity<Double> calculateDistance(@RequestBody CalculateDistanceDto calculateDistanceDto);


}