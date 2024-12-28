package com.quick_bites.mapper;

import com.quick_bites.entity.OrderRecord;
import com.quick_bites.service.managers.order_manager.events.OrderDetails;

import java.util.UUID;

public class OrderDetailsMapper {

    private OrderDetailsMapper() {

    }

    public static OrderDetails mapToOrderPlacedEventDto(OrderRecord order) {

        return new OrderDetails(
                UUID.randomUUID().toString(),
                order.getOrderId() ,
                order.getCustomerId() ,
                order.getPaymentDetails().getPaymentId()
        );
    }

}
