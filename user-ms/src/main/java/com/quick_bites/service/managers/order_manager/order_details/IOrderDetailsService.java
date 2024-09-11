package com.quick_bites.service.managers.order_manager.order_details;


import com.quick_bites.service.user_profile.UserOrderHistory;



public interface IOrderDetailsService {


     UserOrderHistory getUserOrderHistory(Long userId);

}
