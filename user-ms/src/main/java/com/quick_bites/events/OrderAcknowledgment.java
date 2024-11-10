package com.quick_bites.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAcknowledgment {

    private Long orderId;
    private String service;
    private String status;

}
