package com.quick_bites.service.managers.order_manager.cart_manager;

import com.quick_bites.entity.Cart;
import com.quick_bites.entity.CartStatus;

public interface IViewCart {

    Cart viewCart(Long userId , CartStatus status);

}
