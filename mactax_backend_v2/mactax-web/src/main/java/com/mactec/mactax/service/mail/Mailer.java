package com.mactec.mactax.service.mail;

/**
 * Sends out emails.
 */
public interface Mailer {
    /**
     * Send an email based on the given request.
     * 
     * @param mailMessage the email message to send
     */
    void sendEmail(EmailRequest emailRequest);

    /**
     * Send an SMS based on the given request.
     * 
     * @param smsMessage the SMS message to send
     */
    void sendSMS(SmsRequest smsRequest);
}
