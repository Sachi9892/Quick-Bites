package com.quick_bites.service.managers.order_manager.events;

import com.quick_bites.dto.confirm_order_dto.PickOrderDetailsDto;
import com.quick_bites.dto.confirm_order_dto.RestaurantPickOrderDetailsDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.PickOrderDetails;
import com.quick_bites.exceptions.NoResourceFoundException;
import com.quick_bites.mapper.PickOrderDetailsToDto;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.managers.order_manager.events.dto.OrderAcknowledgment;
import com.quick_bites.service.managers.order_manager.rider_manager.ISavePickOrderDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@AllArgsConstructor
@Slf4j
public class PostAcknowledgmentProcessor {

    private final OrderRepository orderRepository;
    private final RestaurantClient restaurantService;
    private final ISavePickOrderDetailsService pickOrderDetailsService;
//    private final SendMobileSMSHelper sendMobileSMSHelper;
    //private final Map<Long, Boolean> processedOrders = new ConcurrentHashMap<>();
    private final PickUpOrderDetailsPublisher pickUpOrderDetailsPublisher;

    public void processPostAcknowledgment(OrderAcknowledgment acknowledgment) {

        Long orderId = acknowledgment.getOrderId();

//        // Prevent duplicate processing
//        boolean isFirstAcknowledgment = processedOrders.putIfAbsent(orderId, true) == null;
//
//        if (!isFirstAcknowledgment) {
//            log.info("Acknowledgment for orderId: {} has already been processed.", orderId);
//            return;
//        }
//
//        log.info("Processing acknowledgment for the first time for orderId: {}", orderId);

        // Retrieve order details
        OrderRecord order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoResourceFoundException("No order found"));

        // Extract required data
        Long restId = order.getRestId();
        Long userId = order.getCustomerId();
        Double userLat = order.getDeliveryAddress().getLatitude();
        Double userLong = order.getDeliveryAddress().getLongitude();

        // Retrieve restaurant details
        RestaurantPickOrderDetailsDto restaurantDetails = restaurantService.sendRestDetails(restId).getBody();


        // Save pick-up order details
        PickOrderDetails orderDetails = pickOrderDetailsService.savePickOrderDetails(orderId, restId, userId, userLat, userLong, restaurantDetails);


        //Create dto of the pick-up details
        PickOrderDetailsDto detailsToSend = PickOrderDetailsToDto.from(orderDetails);


        //Now send the pick-up details via the kafka
        log.info("About to send the pick up details");
        pickUpOrderDetailsPublisher.publishPickOrderDetails(detailsToSend);

// As am out of the twillio free plan, I am skipping this sending sms service
//        try {
//            sendMobileSMSHelper.initializeMessageService(orderId);
//        } catch (Exception e) {
//            log.error("Failed to send SMS notification for orderId: {}. Proceeding with Kafka publishing.", orderId, e);
//        }

        log.info("Mobile sms is sent to the user for orderId: {}", orderId);

    }
}
