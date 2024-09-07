package com.quick_bites.service.managers.order_manager.order_service.impl;


import com.quick_bites.entity.OrderType;
import com.quick_bites.service.managers.order_manager.order_service.ICreateOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class OrderServiceFactory {

    private final CreateOnlinePaymentOrderImpl onlinePaymentOrderService;
    private final CreateCodOrderImpl codOrderService;

    public ICreateOrderService getOrderService(String paymentMode) {

        if (paymentMode.equalsIgnoreCase(String.valueOf(OrderType.ONLINE))) {

            return onlinePaymentOrderService;

        } else if (paymentMode.equalsIgnoreCase(String.valueOf(OrderType.COD))) {

            return codOrderService;

        }

        return null;


    }
}
