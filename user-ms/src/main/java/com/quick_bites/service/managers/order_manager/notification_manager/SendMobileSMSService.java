package com.quick_bites.service.managers.order_manager.notification_manager;


import com.quick_bites.dto.notificationdto.SendMessageDto;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.UserNotFoundException;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.OtpClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SendMobileSMSService {

    private final OtpClient otpClient;
    private final UserRepository userRepository;


    public void sendSMSOnMobileNumber(OrderRecord order) {

        log.debug("Notification service - user ms  , Notification Processing !");

        StringBuilder messageBody = new StringBuilder();

        Long customerId = order.getCustomerId();

        User customer = userRepository
                .findById(customerId)
                .orElseThrow(() -> new UserNotFoundException("Customer Not Found" + customerId));

        messageBody.append("Hey ")
                .append(customer.getUserName())
                .append(", thank you for ordering from \"")
                .append(" Quick-Bites ")
                .append("\"! Your order details: ");

        order.getCart().getCartItems().forEach(dish ->
                messageBody.append("\"")
                        .append(dish.getDishName())
                        .append("\" - ")
                        .append(dish.getQuantity())
                        .append(" unit(s), ")
        );

        messageBody.append("Total amount: ")
                .append(order.getTotalAmount())
                .append(". You will be notified once a rider is assigned for your order.")
                .append(" Thank you for ordering from Quick Bites!");

        // Create SendMessageDto with mobile number and message
        SendMessageDto sendMessageDto = new SendMessageDto(customer.getUserMobileNumber(), messageBody.toString());

        // Send message through OtpClient
        try {
            otpClient.sendMessage(sendMessageDto);
        } catch (Exception e) {
            log.info("Mobile text message failed");
        }

        log.info("Notification service - user ms  , Notification Sent !");


    }

}
