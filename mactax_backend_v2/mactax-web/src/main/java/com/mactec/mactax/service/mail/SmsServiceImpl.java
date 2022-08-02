package com.mactec.mactax.service.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.mactec.mactax.model.ServiceProvider;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.enums.SMSType;
import com.mactec.mactax.model.enums.SmsProvider;
import com.mactec.mactax.service.util.AdminUtil;

@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger LOG = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private AdminUtil adminUtil;

    @Override
    public void sendSMS(SmsRequest smsRequest) throws ServiceException {
        smsRequest = setAdditionalDetails(smsRequest);

        if (smsRequest.getSmsType() == SMSType.ACCOUNT_LOGIN) {
            sendLoginSMS(smsRequest);
        }
    }

    /**
     * 
     * @param smsRequest
     * @throws ServiceException
     */
    private void sendLoginSMS(SmsRequest smsRequest) throws ServiceException {
        smsRequest = smsTemplate.createLoginSmsRequest(smsRequest);
        sendSms(smsRequest);
    }

    /**
     * 
     * @param smsRequest
     */
    private void sendSms(SmsRequest smsRequest) {
        ServiceProvider serviceProvider = smsRequest.getProduct().getServiceProvider();
        if (serviceProvider.getSmsProvider().equals(SmsProvider.AMAZON_SNS)) {
            try {
                LOG.debug("Started sending SMS via AWS SNS.....");
                AWSCredentials awsCredentials = new BasicAWSCredentials(serviceProvider.getSmsSenderId(), serviceProvider.getSmsSenderPassword());
                AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                        .build();
                Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
                smsAttributes.put("awsSenderName", new MessageAttributeValue().withStringValue(serviceProvider.getSmsSenderName()).withDataType("String"));
                smsAttributes.put("awsMaxPrice", new MessageAttributeValue().withStringValue(serviceProvider.getSmsAwsMaxPrice()).withDataType("Number"));
                smsAttributes.put("awsSMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));

                PublishResult result = snsClient
                        .publish(new PublishRequest().withMessage(String.format("%s %s", smsRequest.getSmsMessage(), smsRequest.getOtpNumber()))
                                .withPhoneNumber(smsRequest.getReceiverMobile()).withMessageAttributes(smsAttributes));

                LOG.debug("Finished sending SMS via AWS SNS..... with the result as {}", result);

            } catch (Exception ue) {
                throw new ServiceException("Wasn't able to send SMS");
            }
        } else {
            try {
                LOG.info("Started sending SMS via Indian Provider.....");
                String data = "user=" + URLEncoder.encode(serviceProvider.getSmsSenderId(), "UTF-8");
                data += "&password=" + URLEncoder.encode(serviceProvider.getSmsSenderPassword(), "UTF-8");
                data += "&message=" + URLEncoder.encode(String.format("%s %s", smsRequest.getSmsMessage(), smsRequest.getOtpNumber()), "UTF-8");
                data += "&sender=" + URLEncoder.encode(serviceProvider.getSmsSenderName(), "UTF-8");
                data += "&mobile=" + URLEncoder.encode(smsRequest.getReceiverMobile(), "UTF-8");
                data += "&type=" + URLEncoder.encode("3", "UTF-8");

                URL url = new URL(serviceProvider.getSmsHost());
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = rd.readLine()) != null) {
                    result = result + line + " ";
                }
                wr.close();
                rd.close();
                LOG.info("Finished sending SMS via Indian Provider..... with the result as {}", result);
            } catch (UnsupportedEncodingException ue) {
                throw new ServiceException("Wasn't able to send SMS");
            } catch (MalformedURLException mue) {
                throw new ServiceException("Wasn't able to send SMS");
            } catch (IOException ioe) {
                throw new ServiceException("Wasn't able to send SMS");
            }

        }
    }

    /**
     * 
     * @param smsRequest
     * @return
     */
    private SmsRequest setAdditionalDetails(SmsRequest smsRequest) {
        Users user = adminUtil.getCurrenUser();
        smsRequest.setReceiverName(user.getFirstName() + " " + user.getLastName());
        smsRequest.setReceiverMobile(user.getMobile());
        smsRequest.setUser(user);
        smsRequest.setProduct(user.getProduct());
        smsRequest.setOrganization(user.getOrganization());
        return smsRequest;
    }

}
