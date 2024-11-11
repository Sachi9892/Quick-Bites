package com.quick_bites.service.managers.order_manager.notification_manager;


import com.quick_bites.entity.OrderRecord;
import com.quick_bites.exceptions.OrderNotFoundException;
import com.quick_bites.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProcessingService {

    private final OrderRepository orderRepository;
    private final NotificationManager notificationManager;

    public void processOrderAcknowledgment(Long orderId) {

        OrderRecord order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("No Order found with id" + orderId));

        notificationManager.sendOrderConfirmation(order);
    }

}
