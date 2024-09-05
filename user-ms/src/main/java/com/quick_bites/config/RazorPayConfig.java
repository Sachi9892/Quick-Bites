package com.quick_bites.config;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {


    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.secret.key}")
    private String secretKey;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(apiKey , secretKey);
    }

}