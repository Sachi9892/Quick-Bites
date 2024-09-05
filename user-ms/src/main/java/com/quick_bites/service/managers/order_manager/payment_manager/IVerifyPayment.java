package com.quick_bites.service.managers.order_manager.payment_manager;

import com.razorpay.RazorpayException;

public interface IVerifyPayment {

    boolean verifyPaymentSignature(String paymentId, String signature) throws RazorpayException;

}
