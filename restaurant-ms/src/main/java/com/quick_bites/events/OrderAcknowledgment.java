package com.quick_bites.events;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderAcknowledgment {


    private String eventId;
    private Long orderId;
    private String service;
    private String status;

}
