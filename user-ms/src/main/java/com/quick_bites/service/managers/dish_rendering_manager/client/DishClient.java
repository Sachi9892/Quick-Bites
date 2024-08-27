package com.quick_bites.service.managers.dish_rendering_manager.client;


import com.quick_bites.dto.LocationDto;
import com.quick_bites.dto.ResponseDishDto;
import com.quick_bites.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "RESTAURANT-MS")
public interface DishClient {

    @GetMapping("/dishes/sort")
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


    @GetMapping("/api/geocoding/coordinates")
    LocationDto getCoordinates(@RequestParam("address") String address);

}
