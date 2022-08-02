package com.mactec.mactax.service;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mactec.mactax.config.CustomWebAuthenticationDetails;
import com.mactec.mactax.model.Product;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.dto.OtpDTO;
import com.mactec.mactax.model.enums.EmailType;
import com.mactec.mactax.model.enums.OtpType;
import com.mactec.mactax.model.enums.SMSType;
import com.mactec.mactax.repository.UserRepository;
import com.mactec.mactax.service.mail.EmailRequest;
import com.mactec.mactax.service.mail.Mailer;
import com.mactec.mactax.service.mail.OTPGeneratorUtil;
import com.mactec.mactax.service.mail.SmsRequest;

/**
 * 
 * @author akshaylap
 *
 */
@Service
@Transactional
public class LoginSecurityService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mailer mailer;

    /**
     * 
     * @param auth
     */
    public void checkVerificationCode(Authentication authentication) {

        Users user = userRepository.findByUsername(authentication.getName());
        if (user == null) {
            throw new UsernameNotFoundException("user not found with username " + authentication.getName());
        } else {
            if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
                throw new BadCredentialsException("Invalid user name or password");
            }
        }

        OtpDTO otpDTO = ((CustomWebAuthenticationDetails) authentication.getDetails()).getOtp();

        if (otpDTO == null) {
            otpDTO = new OtpDTO();
        }

        Product product = user.getProduct();

        if (!product.getEmailOtpType().equals(OtpType.NONE) && !product.getSmsOtpType().equals(OtpType.NONE)) {
            boolean sendEmailOtp = validateEmailOtp(otpDTO, user.getEmailOtp());
            boolean sendSmsOtp = validateSmsOtp(otpDTO, user.getSmsOtp());
            if (sendEmailOtp && sendSmsOtp) {
                sendEmailOtp(otpDTO, user);
                sendSmsOtp(otpDTO, user);
            } else {
                user = invalidateOtpAndSaveUser(user); // invalidate or delete OTP after successfull verification
            }
        } else if (!product.getEmailOtpType().equals(OtpType.NONE) && product.getSmsOtpType().equals(OtpType.NONE)) {
            boolean sendEmailOtp = validateEmailOtp(otpDTO, user.getEmailOtp());
            if (sendEmailOtp) {
                sendEmailOtp(otpDTO, user);
            } else {
                user = invalidateOtpAndSaveUser(user); // invalidate or delete OTP after successfull verification
            }
        } else if (product.getEmailOtpType().equals(OtpType.NONE) && !product.getSmsOtpType().equals(OtpType.NONE)) {
            boolean sendSmsOtp = validateSmsOtp(otpDTO, user.getSmsOtp());
            if (sendSmsOtp) {
                sendSmsOtp(otpDTO, user);
            } else {
                user = invalidateOtpAndSaveUser(user); // invalidate or delete OTP after successfull verification
            }
        }
    }

    /**
     * 
     * @param otpDTO
     * @param user
     */
    private void sendEmailOtp(OtpDTO otpDTO, Users user) {
        validateServiceProvider(user.getProduct());
        user = generateEmailOtpAndSaveUser(user);
        mailer.sendEmail(setEmailRequest(user));
    }

    /**
     * 
     * @param otpDTO
     * @param user
     */
    private void sendSmsOtp(OtpDTO otpDTO, Users user) {
        validateServiceProvider(user.getProduct());
        user = generateSmsOtpAndSaveUser(user);
        mailer.sendSMS(setSMSRequest(user));
    }

    /**
     * 
     * @param requestedOtp
     * @param actualOtp
     */
    private boolean validateEmailOtp(OtpDTO otpDTO, String actualOtp) {
        if (otpDTO.getEmailOtp() != null) {
            if (!otpDTO.getEmailOtp().equals(actualOtp)) {
                throw new BadCredentialsException("invalid Email OTP number");
            }
            return false;// OTP validation successfull and don't send email
        }
        return true; // No Email OTP present. generate OTP and send email
    }

    /**
     * 
     * @param requestedOtp
     * @param actualOtp
     */
    private boolean validateSmsOtp(OtpDTO otpDTO, String actualOtp) {
        if (otpDTO.getSmsOtp() != null) {
            if (!otpDTO.getSmsOtp().equals(actualOtp)) {
                throw new BadCredentialsException("invalid Sms OTP number");
            }
            return false; // OTP validation successfull and don't send SMS
        }
        //
        return true; // No SMS OTP present. generate OTP and send SMS
    }

    /**
     * 
     * @param user
     * @return
     */
    private Users generateEmailOtpAndSaveUser(Users user) {
        user.setEmailOtp(OTPGeneratorUtil.generate());
        return userRepository.save(user);
    }

    /**
     * 
     * @param user
     * @return
     */
    private Users generateSmsOtpAndSaveUser(Users user) {
        user.setSmsOtp(OTPGeneratorUtil.generate());
        return userRepository.save(user);
    }

    /**
     * 
     * @return
     */
    private EmailRequest setEmailRequest(Users user) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmailType(EmailType.ACCOUNT_LOGIN);
        emailRequest.setOtpNumber(user.getEmailOtp());
        return emailRequest;
    }

    /**
     * 
     * @return
     */
    private SmsRequest setSMSRequest(Users user) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setSmsType(SMSType.ACCOUNT_LOGIN);
        smsRequest.setOtpNumber(user.getSmsOtp());
        return smsRequest;
    }

    private void validateServiceProvider(Product product) {
        if (product.getServiceProvider() == null) {
            throw new BadCredentialsException("No service provider found. Please map service Provider");
        }
    }

    /**
     * 
     * @param user
     * @return
     */
    private Users invalidateOtpAndSaveUser(Users user) {
        user.setEmailOtp(null);
        user.setSmsOtp(null);
        return userRepository.save(user);
    }

}
