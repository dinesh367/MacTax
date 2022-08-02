package com.mactec.mactax.service.mail;

import org.springframework.stereotype.Component;

/**
 * 
 * @author akshaylap
 *
 */
@Component
public class SmsTemplate {

    protected SmsRequest createLoginSmsRequest(SmsRequest smsRequest) {
        StringBuilder message = new StringBuilder();
        message.append("Hello, Please user login OTP as");
        smsRequest.setSmsMessage(message.toString());
        return smsRequest;
    }

}
