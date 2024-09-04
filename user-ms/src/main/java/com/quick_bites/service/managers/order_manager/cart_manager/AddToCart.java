package com.quick_bites.service.managers.order_manager.cart_manager;

import com.quick_bites.dto.cartdto.AddToCartDto;
import com.quick_bites.entity.Cart;

public interface AddToCart {

    Cart addDishesToCart(AddToCartDto addToCartDto);

}
