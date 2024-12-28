package com.quick_bites.service.managers.order_manager.events;


import com.quick_bites.constants.AppConstants;
import com.quick_bites.entity.OrderRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import static com.quick_bites.mapper.OrderDetailsMapper.mapToOrderPlacedEventDto;

@Service
@AllArgsConstructor
@Slf4j
public class OrderPlaceEvent {

    private final StreamBridge streamBridge;

    public void sendOrderPlacedEvent(OrderRecord order) {

        OrderDetails eventDto = mapToOrderPlacedEventDto(order);
        boolean send = streamBridge.send(AppConstants.ORDER_PLACED_TOPIC, eventDto);
        log.info("Event Sent ? {} , Event : {} " , send , eventDto);


    }
}
