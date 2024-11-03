package com.quick_bite.controller.location_controller;


import com.quick_bite.service.locationiq_services.RoutingService;
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
public class RoutingController {

    private final RoutingService routingService;


    @GetMapping("/route")
    public Mono<ResponseEntity<String>> getRoute(@RequestParam String startCoords,
                                                 @RequestParam String endCoords) {
        return routingService.getRoute(startCoords, endCoords)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
