package com.quick_bites.service.managers.order_manager.order_service.order_now.impl;


import com.quick_bites.entity.OrderType;
import com.quick_bites.service.managers.order_manager.order_service.order_now.ICreateOrderNowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class OrderNowServiceFactory {

    private final ICreateOrderNowService codOrderService;
    private final ICreateOrderNowService onlinePaymentOrderService;

    public ICreateOrderNowService getOrderNowService(String paymentMode) {

        if (paymentMode.equalsIgnoreCase(String.valueOf(OrderType.ONLINE))) {
            return onlinePaymentOrderService;
        }
            return codOrderService;
    }

}
