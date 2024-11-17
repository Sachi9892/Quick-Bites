package com.quick_bites.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {


    private String eventId;
    private Long orderId;
    private Long userId;
    private Long restId;

}
