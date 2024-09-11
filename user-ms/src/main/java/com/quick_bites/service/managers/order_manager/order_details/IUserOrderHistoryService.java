package com.quick_bites.service.managers.order_manager.order_details;

import com.quick_bites.entity.OrderRecord;

public interface IUserOrderHistoryService {

    void addOrderToUserHistory(Long userId, OrderRecord order);

}
