package com.quick_bites.service.managers.order_manager.order_service;

import com.quick_bites.dto.orderdto.OrderRequestDto;
import com.quick_bites.entity.OrderRecord;

public interface ICreateOrderService {

    OrderRecord createOrder(OrderRequestDto orderRequestDto);

}
