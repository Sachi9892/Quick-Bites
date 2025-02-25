package com.quick_bites.service.managers.order_manager.payment_manager;

import com.razorpay.RazorpayException;

public interface ICreateRazorOrder {

    String createRazorpayOrder(Long cartId)  throws RazorpayException;

}
