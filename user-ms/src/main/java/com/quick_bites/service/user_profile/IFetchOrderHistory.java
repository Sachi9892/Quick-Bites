package com.quick_bites.service.user_profile;

import com.quick_bites.dto.orderdto.OrderDetailsDto;

import java.util.List;

public interface IFetchOrderHistory {

    List<OrderDetailsDto> getUserOrders(Long userId);

}
