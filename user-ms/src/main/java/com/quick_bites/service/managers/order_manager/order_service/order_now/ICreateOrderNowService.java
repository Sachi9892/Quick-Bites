package com.quick_bites.service.managers.order_manager.order_service.order_now;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.exceptions.RazorPayException;

public interface ICreateOrderNowService {

    OrderRecord createOrder(PlaceOrderRequestDto placeOrderRequestDto) throws RazorPayException;

}
