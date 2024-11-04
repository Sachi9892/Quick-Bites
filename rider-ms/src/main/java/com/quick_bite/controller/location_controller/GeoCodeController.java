package com.quick_bite.controller.location_controller;


import com.quick_bite.service.locationiq_services.GeoCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/location")
@AllArgsConstructor
public class GeoCodeController {

    private final GeoCodeService geoCodeService;

    @GetMapping("/geocode")
    public Mono<ResponseEntity<String>> getGeocode(@RequestParam String address) {
        return geoCodeService.getGeocode(address)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
