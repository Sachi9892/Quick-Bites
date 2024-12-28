package com.quick_bites.service.managers.order_manager.notification_manager;


import com.quick_bites.entity.OrderRecord;
import com.quick_bites.exceptions.OrderNotFoundException;
import com.quick_bites.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendMobileSMSHelper {

    private final OrderRepository orderRepository;
    private final SendMobileSMSService sendMobileSMSService;

    public void initializeMessageService(Long orderId) {

        OrderRecord order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No Order found with id" + orderId));

        sendMobileSMSService.sendSMSOnMobileNumber(order);
    }

}
