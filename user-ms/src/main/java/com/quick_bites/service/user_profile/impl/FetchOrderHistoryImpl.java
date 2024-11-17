package com.quick_bites.service.user_profile.impl;

import com.quick_bites.dto.dishdto.SingleDishResponseDto;
import com.quick_bites.dto.orderdto.OrderDetailsDto;
import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartItem;
import com.quick_bites.entity.CartStatus;
import com.quick_bites.exceptions.NoOrderHistoryFoundException;
import com.quick_bites.repository.CartItemRepository;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.user_profile.IFetchOrderHistory;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class FetchOrderHistoryImpl implements IFetchOrderHistory {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Cacheable(value = "order_history" , key = "{#userId}")
    public List<OrderDetailsDto> getUserOrders(Long userId) {

        List<Cart> orderedCarts = cartRepository.findAllByUserIdAndStatus(userId, CartStatus.ORDERED);

        if(orderedCarts.isEmpty()) {
            throw new NoOrderHistoryFoundException("No Order History Found For User : " + userId);
        }

        List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();

        for (Cart cart : orderedCarts) {

            List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cart.getCartId());

            List<SingleDishResponseDto> dishResponses = cartItems.stream()
                    .map(item -> new SingleDishResponseDto(
                            item.getDishId(),
                            item.getRestId(),
                            item.getDishName(),
                            item.getPrice(),
                            item.getDishType(),
                            item.getDishPic()))
                    .toList();

            OrderDetailsDto orderDetailsDto = new OrderDetailsDto(
                    dishResponses,
                    cart.getTotalAmount(),
                    cart.getCreatedAt());

             orderDetailsDtos.add(orderDetailsDto);
        }

        return orderDetailsDtos;

    }
}
