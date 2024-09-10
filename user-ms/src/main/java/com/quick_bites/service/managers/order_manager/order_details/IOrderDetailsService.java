package com.quick_bites.service.managers.order_manager.order_details;

import com.quick_bites.dto.orderdto.OrderDetailsDto;

import java.util.List;

public interface IOrderDetailsService {


    List<OrderDetailsDto> getOrdersDetailsById(Long id);

}
