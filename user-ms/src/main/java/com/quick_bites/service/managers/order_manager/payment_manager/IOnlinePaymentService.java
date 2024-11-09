package com.quick_bites.service.managers.order_manager.payment_manager;

import com.quick_bites.entity.Cart;
import com.razorpay.RazorpayException;

public interface IOnlinePaymentService {

    Cart initiateOnlinePayment(Long cartId) throws RazorpayException;

}
