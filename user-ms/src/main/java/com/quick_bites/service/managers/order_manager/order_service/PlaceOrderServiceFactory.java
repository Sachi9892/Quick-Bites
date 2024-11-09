package com.quick_bites.service.managers.order_manager.order_service;


import com.quick_bites.entity.OrderType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlaceOrderServiceFactory {

    private final IPlaceOrderFactory codOrderNowService;
    private final IPlaceOrderFactory onlineOrderNowService;
    private final IPlaceOrderFactory scheduleCodOrderService;
    private final IPlaceOrderFactory scheduleOnlineOrderService;

    // Used @Qualifier to explicitly identify each implementation
    public PlaceOrderServiceFactory(
            @Qualifier("codOrderNowService") IPlaceOrderFactory codOrderNowService,
            @Qualifier("onlineOrderNowService") IPlaceOrderFactory onlineOrderNowService,
            @Qualifier("scheduleCodOrderService") IPlaceOrderFactory scheduleCodOrderService,
            @Qualifier("scheduleOnlineOrderService") IPlaceOrderFactory scheduleOnlineOrderService
    ) {
        this.codOrderNowService = codOrderNowService;
        this.onlineOrderNowService = onlineOrderNowService;
        this.scheduleCodOrderService = scheduleCodOrderService;
        this.scheduleOnlineOrderService = scheduleOnlineOrderService;
    }

    public IPlaceOrderFactory placeOrder(OrderType orderType) {
        return switch (orderType) {
            case COD -> codOrderNowService;
            case ONLINE -> onlineOrderNowService;
            case SCHEDULE_COD -> scheduleCodOrderService;
            case SCHEDULE_ONLINE -> scheduleOnlineOrderService;
        };

    }


}
