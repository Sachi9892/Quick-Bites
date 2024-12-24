package com.quick_bites.controller.geocoding;


import com.quick_bites.dto.location_dto.LocationDto;
import com.quick_bites.location_service.GeoCodingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant/geocoding")
@AllArgsConstructor
@Slf4j
public class GeoCodingController {

    private final GeoCodingService geoCodingService;

    @GetMapping("/coordinates")
    public ResponseEntity<LocationDto> getCoordinates(
            @RequestParam("address") String address) {
        LocationDto coordinates = geoCodingService.getCoordinates(address);
        return ResponseEntity.ok(coordinates);
    }

}