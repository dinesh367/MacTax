package com.mactec.mactax.service.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mactec.mactax.model.ServiceProvider;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.enums.EmailType;
import com.mactec.mactax.service.util.AdminUtil;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailTemplate emailTemplate;

    @Autowired
    private AdminUtil adminUtil;

    @Override
    public void sendEmail(EmailRequest emailRequest) throws ServiceException {

        emailRequest = setAdditionalDetails(emailRequest);

        if (emailRequest.getEmailType() == EmailType.COMMUNICATION) {
            sendCommunicationEmail(emailRequest);
        }

        if (emailRequest.getEmailType() == EmailType.ACCOUNT_REGISTRATION || emailRequest.getEmailType() == EmailType.ACCOUNT_REGISTRATION_WITH_OTP) {
            sendRegisterWithOrWithoutOTPEmail(emailRequest);
        }

        if (emailRequest.getEmailType() == EmailType.ACCOUNT_LOGIN) {
            sendLoginWithOtpEmail(emailRequest);
        }

        if (emailRequest.getEmailType() == EmailType.ORDER_CONFIRMATION) {
            sendOrderConfirmationEmail(emailRequest);
        }

        if (emailRequest.getEmailType() == EmailType.PASSWORD_RESET) {
            sendForgotPasswordEmail(emailRequest);
        }

    }

    private void sendCommunicationEmail(EmailRequest emailRequest) throws ServiceException {
        emailRequest = emailTemplate.createCommunicationEmailRequest(emailRequest);
        sendEmail(emailRequest, " | Communication");
    }

    private void sendRegisterWithOrWithoutOTPEmail(EmailRequest emailRequest) throws ServiceException {
        emailRequest = emailTemplate.createRegisterWithOrWithoutOTPEmailRequest(emailRequest);
        sendEmail(emailRequest, " | Registration");
    }

    private void sendLoginWithOtpEmail(EmailRequest emailRequest) throws ServiceException {
        emailRequest = emailTemplate.createLoginWithOtpEmailRequest(emailRequest);
        sendEmail(emailRequest, " | Login");
    }

    private void sendOrderConfirmationEmail(EmailRequest emailRequest) throws ServiceException {
        emailRequest = emailTemplate.createOrderConfirmationEmailRequest(emailRequest);
        sendEmail(emailRequest, " | Order Confirmation");
    }

    private void sendForgotPasswordEmail(EmailRequest emailRequest) throws ServiceException {
        emailRequest = emailTemplate.createForgotPasswordEmailRequest(emailRequest);
        sendEmail(emailRequest, " | Forgotten Password");
    }

    /**
     * 
     * 
     * @param emailRequest
     * @param subject
     * @throws ServiceException
     */
    private void sendEmail(EmailRequest emailRequest, String subject) throws ServiceException {
        LOG.debug("Emailing via SMTP.....");

        ServiceProvider serviceProvider = emailRequest.getProduct().getServiceProvider();

        Transport transport = null;
        try {
            Session mailSession = Session.getDefaultInstance(getSessionProperties(serviceProvider), getSessionAuthenticator(serviceProvider));
            mailSession.setDebug(serviceProvider.isEmailDebug());
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(emailRequest.getProductName() + subject);
            message.setContent(emailRequest.getHtmlEmailBody(), "text/html");
            // Your domain email
            message.setFrom(new InternetAddress(serviceProvider.getEmailSenderName()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRequest.getToAddress())); // Send email To (Type email ID
            // message.addRecipient(Message.RecipientType.CC, new InternetAddress("support@kayrasolutions.com")); // Send email To (Type email ID
            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
        } catch (NoSuchProviderException e) {
            LOG.error(e.getMessage());
            throw new ServiceException("No Service provider found with given properties");
        } catch (MessagingException e) {
            LOG.error(e.getMessage());
            throw new ServiceException("Error sending email.");
        } finally {
            if (null != transport) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    LOG.error(e.getMessage());
                    throw new ServiceException("Error closing SMTP connection.");
                }
            }
        }

        LOG.debug("Finished Emailing via SMTP.....");
    }

    /**
     * 
     * 
     * @param serviceProvider
     * @return
     */
    private Properties getSessionProperties(ServiceProvider serviceProvider) {

        final String protocol = serviceProvider.getEmailTrasportProtocol();
        final String hostType = serviceProvider.getEmailHostType();
        final String hostName = serviceProvider.getEmailHostName();
        final String hostPort = serviceProvider.getEmailHostPort();
        final String authType = serviceProvider.getEmailAuthType();
        final boolean trasportAuth = serviceProvider.isEmailTrasportAuth();

        Properties props = new Properties();
        props.put("mail.transport.protocol", protocol);
        props.put(hostType, hostName);
        props.put(protocol.equalsIgnoreCase("smtps") ? "mail.smtps.port" : "mail.smtp.port", hostPort);
        props.put(authType, trasportAuth);
        props.put("mail.smtp.starttls.enable", "true");

        return props;
    }

    /**
     * 
     * 
     * @param serviceProvider
     * @return
     */
    private Authenticator getSessionAuthenticator(ServiceProvider serviceProvider) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(serviceProvider.getEmailAuthUser(), serviceProvider.getEmailAuthPassword());
            }
        };
    }

    /**
     * 
     * @param emailRequest
     * @return
     */
    private EmailRequest setAdditionalDetails(EmailRequest emailRequest) {
        Users user = adminUtil.getCurrenUser();
        emailRequest.setReceiverName(user.getFirstName() + " " + user.getLastName());
        emailRequest.setToAddress(user.getEmail());
        emailRequest.setProductName(user.getProduct().getName());
        emailRequest.setUser(user);
        emailRequest.setProduct(user.getProduct());
        emailRequest.setOrganization(user.getOrganization());
        return emailRequest;
    }

}
