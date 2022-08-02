/**
 * 
 */
package com.mactec.mactax.service.mail;

import org.hibernate.service.spi.ServiceException;

/**
 * SMS Service interface defining methods for sending OTP or other SMS
 * 
 * @author Rajeev Dubey
 *
 */
public interface SmsService {

    /**
     * Sends the SMS based on the supplied {@link SmsRequest}.
     *
     * @param message the {@link SmsRequest} containing details of the message to be sent
     */
    void sendSMS(SmsRequest message) throws ServiceException;
}
