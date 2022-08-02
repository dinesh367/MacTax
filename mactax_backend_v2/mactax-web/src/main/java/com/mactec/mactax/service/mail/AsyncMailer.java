package com.mactec.mactax.service.mail;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mactec.mactax.service.exception.MailerException;

/**
 * A Mailer implementation that mails asynchronously in a separate thread.
 * 
 * @author akshayp
 */
@Component
public final class AsyncMailer implements Mailer {

    private final Logger logger = LoggerFactory.getLogger(AsyncMailer.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("smsServiceImpl")
    private SmsService smsService;

    @Async
    @Override
    public void sendEmail(final EmailRequest emailRequest) {
        try {
            emailService.sendEmail(emailRequest);
        } catch (RuntimeException e) {
            logger.error("Problem sending email", e);
            throw new MailerException("Failed to send email request" + emailRequest);
        }
    }

    @Async
    @Override
    public void sendSMS(SmsRequest smsRequest) {
        try {
            smsService.sendSMS(smsRequest);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            logger.error("Problem sending sms", e);
            throw new MailerException("Failed to send email request" + smsRequest);
        }
    }

}
