package com.quick_bite.service.rider_manager.rider_assignment;


import com.quick_bite.dto.order_dto.PickOrderDetailsDto;
import com.quick_bite.dto.order_dto.PickUpOrderFullDetails;
import com.quick_bite.dto.rider_dto.RiderDetails;
import com.quick_bite.service.utility.DistanceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AssignOrderToRiderService {

    private final FindNearestRiderService findNearestRiderService;

    public PickUpOrderFullDetails assignOrderToRider(PickOrderDetailsDto orderDetails) {

        // Step 1: Find the nearest free rider
        RiderDetails nearestRider = findNearestRiderService.findNearestFreeRider(
                orderDetails.getRestaurantLatitude(),
                orderDetails.getRestaurantLongitude()
        );

        if (nearestRider == null) {
            log.warn("No free riders available for Order ID: {}", orderDetails.getOrderId());
            return null; // Handle appropriately
        }

        log.info("Nearest rider found: {} for Order ID: {}", nearestRider.getRiderId(), orderDetails.getOrderId());

        // Step 2: Calculate distances
        double pickUpDistance = DistanceCalculator.calculateDistance(
                orderDetails.getRestaurantLatitude(),
                orderDetails.getRestaurantLongitude(),
                nearestRider.getLatitude(),
                nearestRider.getLongitude()
        );

        double dropDistance = DistanceCalculator.calculateDistance(
                orderDetails.getRestaurantLatitude(),
                orderDetails.getRestaurantLongitude(),
                orderDetails.getUserLatitude(),
                orderDetails.getUserLongitude()
        );

        // Step 3: Estimate earnings
        double earning = calculateEarnings(pickUpDistance, dropDistance);

        // Step 4: Update rider status to ON_DELIVERY
        nearestRider.setStatus("ON_DELIVERY");
        log.info("Assigned Rider {} to Order ID: {}. Pickup Distance: {}, Drop Distance: {}, Earning: {}",
                nearestRider.getRiderId(), orderDetails.getOrderId(), pickUpDistance, dropDistance, earning);


        // Step 5: Return the complete order details
        PickUpOrderFullDetails newOrder = new PickUpOrderFullDetails();

        newOrder.setOrderId(orderDetails.getOrderId());
        newOrder.setRiderId(nearestRider.getRiderId());
        newOrder.setRestaurantLatitude(orderDetails.getRestaurantLatitude());
        newOrder.setRestaurantLongitude(orderDetails.getRestaurantLongitude());
        newOrder.setUserLatitude(orderDetails.getUserLatitude());
        newOrder.setUserLongitude(orderDetails.getUserLongitude());
        newOrder.setPickUpDistance(pickUpDistance);
        newOrder.setDropDistance(dropDistance);
        newOrder.setEarning(earning);
        newOrder.setRestaurantAddress(orderDetails.getRestaurantAddress());
        newOrder.setRestaurantName(orderDetails.getRestaurantName());

        return newOrder;
    }

    private double calculateEarnings(double pickUpDistance, double dropDistance) {
        // Example earning logic: Base fare + distance-based fare
        double baseFare = 20.0;
        double perKmRate = 5.0;
        return baseFare + (pickUpDistance + dropDistance) * perKmRate;
    }

}
