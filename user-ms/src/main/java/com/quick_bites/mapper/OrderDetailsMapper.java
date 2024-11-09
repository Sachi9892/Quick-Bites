package com.quick_bites.mapper;

import com.quick_bites.entity.OrderRecord;
import com.quick_bites.events.OrderDetails;

public class OrderDetailsMapper {

    private OrderDetailsMapper() {

    }

    public static OrderDetails mapToOrderPlacedEventDto(OrderRecord order) {

        return new
                OrderDetails(order.getOrderId() ,
                order.getCustomerId() ,
                order.getPaymentDetails().getPaymentId());
    }

}
