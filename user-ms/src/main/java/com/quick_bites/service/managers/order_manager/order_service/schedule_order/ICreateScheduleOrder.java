package com.quick_bites.service.managers.order_manager.order_service.schedule_order;

import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.exceptions.RazorPayException;

public interface ICreateScheduleOrder {

    OrderRecord createScheduleOrder(PlaceOrderRequestDto scheduleOrderDto) throws RazorPayException;

}
