package com.quick_bites.service.managers.order_manager.payment_manager.impl;



import com.quick_bites.entity.Cart;
import com.quick_bites.entity.User;
import com.quick_bites.exceptions.RazorPayException;
import com.quick_bites.exceptions.UserNotFoundException;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.repository.UserRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class CreateRazorOrderImpl implements ICreateRazorOrder {

    private final RazorpayClient razorpayClient;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public String createRazorpayOrder(Long cartId , Long userId) throws RazorpayException {

        try {

            Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoResourceFoundException("No cart"));
            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

            JSONObject options = new JSONObject();
            options.put("amount", (int) (cart.getTotalAmount() * 100));
            options.put("currency", "INR");
            options.put("payment_capture", 1);
            options.put("name" , user.getUserName());
            options.put("email" , user.getUserEmail());
            options.put("mobile" , user.getUserMobileNumber());


            Order razorpayOrder = razorpayClient.orders.create(options);

            return razorpayOrder.get("id");

        } catch (Exception e) {
            throw  new RazorPayException("Razor Pay Is Down !");
        }

    }

}
