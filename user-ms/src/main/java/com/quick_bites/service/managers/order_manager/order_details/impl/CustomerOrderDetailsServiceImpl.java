package com.quick_bites.service.managers.order_manager.order_details.impl;

import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import com.quick_bites.dto.orderdto.OrderDetailsDto;
import com.quick_bites.entity.CartItem;
import com.quick_bites.entity.OrderRecord;
import com.quick_bites.repository.OrderRepository;
import com.quick_bites.service.managers.dish_rendering_manager.feign_client.RestaurantClient;
import com.quick_bites.service.managers.order_manager.order_details.IOrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CustomerOrderDetailsServiceImpl implements IOrderDetailsService {

    private final OrderRepository orderRepository;
    private final RestaurantClient restaurantClient;


    @Override
    public List<OrderDetailsDto> getOrdersDetailsById(Long id) {


        List<OrderRecord> orders = orderRepository.findByUser_UserId(id);

        // Map orders to DTOs
        List<OrderDetailsDto> orderDetails = new ArrayList<>();

        for(OrderRecord order :orders) {

            // Fetch dish details
            List<SingleDishResponseDto> dishDtos = new ArrayList<>();

            for (CartItem item : order.getCart().getCartItems()) {

                SingleDishResponseDto dishDto = restaurantClient.getSingleDishMethod(item.getDishId()).getBody();

                if (dishDto != null) {
                    dishDtos.add(dishDto);
                }

            }

            // Create OrderDetailsDto

            OrderDetailsDto detailsDto = new OrderDetailsDto(
                    dishDtos,
                    order.getTotalAmount(),
                    order.getOrderDate()
            );

            orderDetails.add(detailsDto);
        }

        return orderDetails;

    }
}
