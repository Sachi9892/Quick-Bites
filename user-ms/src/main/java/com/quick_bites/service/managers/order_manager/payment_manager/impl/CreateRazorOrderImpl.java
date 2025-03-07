package com.quick_bites.service.managers.order_manager.payment_manager.impl;


import com.quick_bites.entity.Cart;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;


@Service
@AllArgsConstructor
@Slf4j
public class CreateRazorOrderImpl implements ICreateRazorOrder {

    private final RazorpayClient razorpayClient;
    private final CartRepository cartRepository;


    public String createRazorpayOrder(Long cartId , Long userId) throws RazorpayException {

        try {

            Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoResourceFoundException("No cart"));

            JSONObject options = new JSONObject();
            options.put("amount", (int) (cart.getTotalAmount() * 100));
            options.put("currency", "INR");
            options.put("payment_capture", 1);


            Order razorpayOrder = razorpayClient.orders.create(options);

            String orderId = razorpayOrder.get("id");

            JSONObject response = new JSONObject();
            response.put("id", orderId);

            log.info("Razor order : {}", response.toString());

            return response.toString();

        } catch (Exception e) {

            log.info("Inside catch of the create razor pay : {} " , e.getMessage());
            throw new RazorPayException("Razor Pay Is Down !");
        }

    }

}
