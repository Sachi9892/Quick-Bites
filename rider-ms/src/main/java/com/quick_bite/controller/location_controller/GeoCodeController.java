package com.quick_bite.controller.location_controller;


import com.quick_bite.dto.LocationDto;
import com.quick_bite.service.locationiq_services.GeoCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/location")
@AllArgsConstructor
public class GeoCodeController {

    private final GeoCodeService geoCodeService;

    @GetMapping("/geocode")
    public ResponseEntity<LocationDto> getGeocode(@RequestParam String address) {

        LocationDto locationDto = geoCodeService.getGeocode(address);
        return ResponseEntity.ok(locationDto);

    }

}
