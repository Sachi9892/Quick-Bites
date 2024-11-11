package com.quik_bites.service.otp_manager;

public interface ISendMessageService {

    boolean sendMessage(String mobileNumber , String messageBody);

}
