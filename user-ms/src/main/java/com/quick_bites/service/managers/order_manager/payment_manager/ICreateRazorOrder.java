package com.quick_bites.service.managers.order_manager.payment_manager;

import com.quick_bites.dto.paymentdto.CartPaymentDto;
import com.razorpay.RazorpayException;

public interface ICreateRazorOrder {

    String createRazorpayOrder(Long cartId)  throws RazorpayException;

}
