package com.quick_bites.dto.orderdto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.quick_bites.entity.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceOrderRequestDto implements Serializable {

    private Long cartId;
    private Long deliveryAddress;
    private OrderType orderType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalTime scheduledTime;

}
