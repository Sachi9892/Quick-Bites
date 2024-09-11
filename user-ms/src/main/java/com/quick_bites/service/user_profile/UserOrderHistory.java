package com.quick_bites.service.user_profile;


import com.quick_bites.dto.orderdto.OrderDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class UserOrderHistory {

    private Long userId;
    private List<OrderDetailsDto> orders;

}
