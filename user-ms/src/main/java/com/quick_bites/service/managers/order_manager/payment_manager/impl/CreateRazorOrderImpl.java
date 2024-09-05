package com.quick_bites.service.managers.order_manager.payment_manager.impl;




import com.quick_bites.entity.Cart;
import com.quick_bites.entity.PaymentDetails;
import com.quick_bites.entity.PaymentStatus;
import com.quick_bites.repository.CartRepository;
import com.quick_bites.repository.PaymentDetailsRepository;
import com.quick_bites.service.managers.order_manager.payment_manager.ICreateRazorOrder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CreateRazorOrderImpl implements ICreateRazorOrder {

    private final RazorpayClient razorpayClient;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private CartRepository cartRepository;

    public String createRazorpayOrder(Long cartId) throws RazorpayException {

        Optional<Cart> cart = cartRepository.findById(cartId);

        if(cart.isEmpty()) {
            log.info("Wait we fetching cart details");
        }

        JSONObject options = new JSONObject();

        options.put("amount", (int) ( cart.get().getTotalAmount() * 100));
        options.put("User_Id" , cart.get().getUserId());
        options.put("Restaurant_Id" , cart.get().getRestId());
        options.put("Total_Amount" , cart.get().getTotalAmount());
        options.put("Total_Dishes" , cart.get().getTotalDishes());
        options.put("currency", "INR");
        options.put("payment_capture", true);


        Order order = razorpayClient.orders.create(options);
        log.info("Order id - {} " , order);

        PaymentDetails payment = new PaymentDetails();

        //Now we just need cart id
        payment.setTotalAmount(cart.get().getTotalAmount());

        payment.setUserId(cart.get().getUserId());

        payment.setCartId(cart.get().getCartId());

        payment.setRestaurantId(cart.get().getRestId());

        //For our use
        payment.setTransactionId(UUID.randomUUID().toString());

        payment.setPaymentStatus(PaymentStatus.CREATED);

        payment.setModeOfPayment("ONLINE");

        payment.setPaymentTime(LocalDateTime.now());

        payment.setRazorpayOrderId(order.get("id"));

        paymentDetailsRepository.save(payment);

        return order.toString();

    }



}
