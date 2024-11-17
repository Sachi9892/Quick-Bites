package com.quick_bite.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private String eventId;
    private Long orderId;
    private Long userId;
    private Long restId;

}
