package com.quick_bites.service.managers.order_manager.order_details.impl;


import com.quick_bites.entity.OrderRecord;
import com.quick_bites.entity.User;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.order_manager.order_details.IUserOrderHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserOrderHistoryServiceImpl implements IUserOrderHistoryService {

    private final UserRepository userRepository;


    @Override
    public void addOrderToUserHistory(Long userId, OrderRecord order) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        //user.getOrderHistory().add(order);
        userRepository.save(user);

    }

}
