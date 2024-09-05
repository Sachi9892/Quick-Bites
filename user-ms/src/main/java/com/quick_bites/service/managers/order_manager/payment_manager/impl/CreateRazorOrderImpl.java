package com.quick_bites.service.managers.order_manager.payment_manager.impl;



import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateRazorOrderImpl implements com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder {

    private final RazorpayClient razorpayClient;

    public String createRazorpayOrder(Double amount, String receiptId) throws RazorpayException {

        JSONObject options = new JSONObject();
        options.put("amount", (int) (amount * 100));  // Razorpay accepts amount in paise
        options.put("currency", "INR");
        options.put("receipt", receiptId);
        options.put("payment_capture", true);  // Automatic capture

        Order order = razorpayClient.orders.create(options);
        return order.toString();  // Return Razorpay order details
    }



}
