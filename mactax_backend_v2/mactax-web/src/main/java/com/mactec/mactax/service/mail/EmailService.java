package com.mactec.mactax.service.mail;

import org.hibernate.service.spi.ServiceException;
import org.springframework.mail.MailMessage;

/**
 * Email Service interface defining methods for sending emails
 */
public interface EmailService {

    /**
     * Sends the email based on the supplied {@link MailMessage}.
     *
     * @param message the {@link MailMessage} containing details of the message to be sent
     */
    void sendEmail(EmailRequest emailRequest) throws ServiceException;
}
