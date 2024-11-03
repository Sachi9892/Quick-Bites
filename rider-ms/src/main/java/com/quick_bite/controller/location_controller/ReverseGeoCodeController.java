package com.quick_bite.controller.location_controller;


import com.quick_bite.service.locationiq_services.ReverseGeocodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;


@Controller
@AllArgsConstructor
@RequestMapping("/location")
public class ReverseGeoCodeController {

    private final ReverseGeocodeService reverseGeocodeService;

    @GetMapping("/reverse-geocode")
    public Mono<ResponseEntity<String>> getReverseGeocode(@RequestParam double lat, @RequestParam double lon) {
        return reverseGeocodeService.getReverseGeocode(lat, lon)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

}
