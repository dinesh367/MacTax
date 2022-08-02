package com.mactec.mactax.service.mail;

import com.mactec.mactax.model.Organization;
import com.mactec.mactax.model.Product;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.enums.SMSType;

import lombok.Data;

/**
 * Encapsulates a SMS message with all the required details for sending SMS message
 */
@Data
public class SmsRequest {

    private Users user;

    private Product product;

    private Organization organization;

    private SMSType smsType;

    private String receiverMobile;

    private String receiverName;

    private String smsMessage;

    private String otpNumber;
}
