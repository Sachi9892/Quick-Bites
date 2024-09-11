package com.quick_bites.service.managers.order_manager.cart_manager;


import com.quick_bites.dto.cartdto.RemoveFromCartDto;
import com.quick_bites.entity.Cart;

public interface IRemoveFromCart {

    Cart removeFromCart(RemoveFromCartDto removeFromCartDto);

}
