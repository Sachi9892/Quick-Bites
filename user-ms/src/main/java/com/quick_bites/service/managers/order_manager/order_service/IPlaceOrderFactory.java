package com.quick_bites.service.managers.order_manager.order_service;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.razorpay.RazorpayException;

public interface IPlaceOrderFactory {

    OrderRecord placeOrder(PlaceOrderRequestDto requestDto) throws RazorpayException;

}
