package com.quick_bites.controller.geocoding;


import com.quick_bites.dto.location_dto.CalculateDistanceDto;
import com.quick_bites.location_service.impl.CalculateDistanceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class CalculateDistanceController {

    private final CalculateDistanceServiceImpl calculateDistanceService;

    @GetMapping("/calculate/distance")
    public ResponseEntity<Double> calculateDistance(@RequestBody CalculateDistanceDto calculateDistanceDto) {

        Double distance = calculateDistanceService
                .calculateDistance(
                calculateDistanceDto.getStartLatitude(), calculateDistanceDto.getStartLongitude(),
                calculateDistanceDto.getEndLatitude(), calculateDistanceDto.getEndLongitude());

        return ResponseEntity.ok(distance);
    }
}
