package com.quick_bite.client;

import com.quick_bite.dto.LocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "RESTAURANT-MS")
public interface RestaurantClient {

    @GetMapping("/api/geocoding/coordinates")
    LocationDto getCoordinates(@RequestParam("address") String address);


}
