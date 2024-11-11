package com.quik_bites.service.otp_manager.impl;

import com.quik_bites.config.TwilioConfig;
import com.quik_bites.exception.TwillioDownException;
import com.quik_bites.service.otp_manager.ISendMessageService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class SendMessageImpl implements ISendMessageService {

    private final TwilioConfig twilioConfig;

    @Override
    public boolean sendMessage(String mobileNumber, String messageBody) {

        try {

            // Receiver (Customer, Restaurant, Rider)
            PhoneNumber to = new PhoneNumber(mobileNumber);

            // Sender (Quick - bites)
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            //Send otp
            Message message = Message.creator(to, from, messageBody).create();
            log.info("Message  :   {} " , message);

            return true;

        } catch (TwillioDownException ex) {
            log.info("Twilio Down {}" , ex.getMessage());
            return false;
        }

    }
}
