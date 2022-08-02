package com.mactec.mactax.service.mail;

import com.mactec.mactax.model.Organization;
import com.mactec.mactax.model.Product;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.enums.EmailType;

import lombok.Data;

/**
 * Encapsulates the request for email to Connecting Element
 */
@Data
public class EmailRequest {

    private Users user;

    private Product product;

    private Organization organization;

    private EmailType emailType;

    private String receiverName;

    private String subject;

    private String fromAddress;

    private String toAddress;

    private String htmlEmailBody;

    private String communicationMessage;

    private String productName;

    private String otpNumber;

    private String resetLink;

    private String amount;

    private String collectionDate;

}
