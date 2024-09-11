package com.quick_bites.service.managers.order_manager.order_details.impl;

import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import com.quick_bites.dto.orderdto.OrderDetailsDto;
import com.quick_bites.entity.CartItem;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.service.managers.order_manager.order_details.IOrderDetailsService;
import com.quick_bites.service.user_profile.UserOrderHistory;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CustomerOrderDetailsServiceImpl implements IOrderDetailsService {

    private final OrderRepository orderRepository;

    @Override
    public UserOrderHistory getUserOrderHistory(Long userId) {

        List<OrderRecord> orders = orderRepository.findByUser_UserId(userId);
        List<OrderDetailsDto> orderDetails = new ArrayList<>();

        for (OrderRecord order : orders) {
            List<SingleDishResponseDto> dishDtos = getSingleDishResponseDtos(order);
            OrderDetailsDto detailsDto = new OrderDetailsDto(
                    dishDtos,
                    order.getTotalAmount(),
                    order.getOrderDate()
            );
            orderDetails.add(detailsDto);
        }

        return new UserOrderHistory(userId, orderDetails);
    }

    private static @NotNull List<SingleDishResponseDto> getSingleDishResponseDtos(OrderRecord order) {
        List<SingleDishResponseDto> dishDtos = new ArrayList<>();
        for (CartItem item : order.getCart().getCartItems()) {
            SingleDishResponseDto dishDto = new SingleDishResponseDto(
                    item.getDishId(),
                    item.getRestId(),
                    item.getDishName(),
                    item.getPrice(),
                    item.getDishType(),
                    item.getDishPic()
            );
            dishDtos.add(dishDto);
        }

        return dishDtos;

    }
}
