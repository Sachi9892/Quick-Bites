package com.quick_bites.service.managers.order_manager.events.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAcknowledgment {

    private String eventId;
    private Long orderId;
    private String service;
    private String status;

}
