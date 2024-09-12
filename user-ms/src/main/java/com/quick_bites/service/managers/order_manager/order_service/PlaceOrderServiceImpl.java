package com.quick_bites.service.managers.order_manager.order_service;


import com.quick_bites.dto.orderdto.PlaceOrderRequestDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.OrderType;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import com.quick_bites.service.managers.order_manager.order_service.schedule_order.ICreateScheduleOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceOrderServiceImpl implements IPlaceOrderFactory {

    private final ICreateOrderNowService codOrderService;
    private final ICreateOrderNowService onlineOrderService;
    private final ICreateScheduleOrder scheduleCodOrderService;
    private final ICreateScheduleOrder scheduleOnlineOrderService;

    @Override
    public OrderRecord placeOrder(PlaceOrderRequestDto requestDto) throws Exception {

        boolean isScheduled = requestDto.getScheduledTime() != null;
        OrderType orderType = requestDto.getOrderType();

        if (isScheduled) {
            return switch (orderType) {
                case SCHEDULE_COD -> scheduleCodOrderService.createScheduleOrder(requestDto);
                case SCHEDULE_ONLINE -> scheduleOnlineOrderService.createScheduleOrder(requestDto);
                default -> throw new IllegalArgumentException("Invalid order type for scheduled orders");
            };
        } else {
            return switch (orderType) {
                case COD -> codOrderService.createOrder(requestDto);
                case ONLINE -> onlineOrderService.createOrder(requestDto);
                default -> throw new IllegalArgumentException("Invalid order type for regular orders");
            };
        }
    }

}
