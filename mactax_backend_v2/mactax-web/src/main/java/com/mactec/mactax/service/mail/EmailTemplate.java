package com.mactec.mactax.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mactec.mactax.service.util.AdminUtil;

@Component
public class EmailTemplate {

    @Autowired
    private AdminUtil adminUtil;

    protected EmailRequest createCommunicationEmailRequest(EmailRequest emailRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("Hello " + emailRequest.getUser().getFirstName() + ",");
        sb.append("<br/><br/>");
        sb.append("You have received a new communication. <br/>");
        sb.append(emailRequest.getCommunicationMessage());
        sb.append("<br/><br/>");
        sb.append("Warm Regards,");
        sb.append("<br/>");
        sb.append("Team " + emailRequest.getProduct().getName());
        sb.append("<br/><br/><br/><br/>");
        sb.append("**This is a system generated mail, please do not reply on this mail.");
        sb.append("<br/></body></html>");
        emailRequest.setHtmlEmailBody(sb.toString());

        return emailRequest;
    }

    protected EmailRequest createRegisterWithOrWithoutOTPEmailRequest(EmailRequest emailRequest) {
        StringBuffer html = new StringBuffer();
        if (adminUtil.isTaxPayer()) {
            html.append("<p>Thank you " + emailRequest.getReceiverName() + " for registering with " + emailRequest.getProductName() + " as Tax Payer.</p>");
            html.append("<br>");
            html.append("<span>We appreciate you for choosing our services for seamless Tax Filing experience. Our platform with</span>");
            html.append("<br>");
            html.append("<span>intuitive user experience and simple navigation is aimed at making your Tax Filing process smooth</span>");
            html.append("<br>");
            html.append("<span>and quick.</span>");
            html.append("<br>");
        } else if (adminUtil.isTaxConsutant()) {
            html.append(
                    "<p> Thank you " + emailRequest.getReceiverName() + " for registering with " + emailRequest.getProductName() + " as Tax Consultant.</p>");
            html.append("<br>");
            html.append("<span>We are elated to have you onboard with us to provide seamless services for Tax Payers. Our platform</span>");
            html.append("<br>");
            html.append("<span>ensures eased process for Tax Consultants with control and visibility for Tax Filing process.</span>");
            html.append("<br>");
        }
        if (emailRequest.getOtpNumber() != null) {
            html.append("<p>Please use your OTP as " + emailRequest.getOtpNumber() + " to login.</p>");
        }
        html.append("<br>");
        html.append("<span>Warm Regards</span>");
        html.append("<br>");
        html.append("<span>Team " + emailRequest.getProductName() + "</span>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<p>**This is a system generated mail, please do not reply on this mail.</p>");
        emailRequest.setHtmlEmailBody(html.toString());

        return emailRequest;
    }

    protected EmailRequest createLoginWithOtpEmailRequest(EmailRequest emailRequest) {
        StringBuffer html = new StringBuffer();
        html.append("<p>Hello " + emailRequest.getReceiverName());
        html.append("<p>We noticed you are logging-in to the tool.</p>");
        html.append("<p>Please use your OTP as " + emailRequest.getOtpNumber() + "</p>");
        html.append("<br>");
        html.append("<span>Warm Regards</span>");
        html.append("<br>");
        html.append("<span>Team " + emailRequest.getProductName() + "</span>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<p>**This is a system generated mail, please do not reply on this mail.</p>");
        emailRequest.setHtmlEmailBody(html.toString());
        return emailRequest;
    }

    protected EmailRequest createForgotPasswordEmailRequest(EmailRequest emailRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("Hello " + emailRequest.getReceiverName());
        sb.append("<br/><br/>");
        sb.append("We have received your reset password request, please click on the reset link or copy and paste in the browser to set your new password.");
        sb.append("<br/><br/>");
        sb.append("<a href=\"" + emailRequest.getResetLink() + "\" target=\"blank\">Reset Password</a><br/>");
        sb.append("<br/><br/>");
        sb.append("Warm Regards,");
        sb.append("<br/>");
        sb.append("Team " + emailRequest.getProductName());
        sb.append("<br/><br/><br/><br/>");
        sb.append("**This is a system generated mail, please do not reply on this mail.");
        sb.append("<br/></body></html>");
        emailRequest.setHtmlEmailBody(sb.toString());
        return emailRequest;
    }

    protected EmailRequest createOrderConfirmationEmailRequest(EmailRequest emailRequest) {
        StringBuffer html = new StringBuffer();
        html.append("<p>Hello " + emailRequest.getReceiverName() + "</p>");
        html.append("<br>");
        html.append("<span>Your payment for the subscription of Tax Filing Service on " + emailRequest.getProduct().getDomain() + " is successful.</span>");
        html.append("<br>");
        html.append(
                "<span>The subscription amount Rs." + emailRequest.getAmount() + " paid on " + emailRequest.getCollectionDate() + " has been received.</span>");
        html.append("<br>");
        html.append("<p>We are happy to have you onboard.</p>");
        html.append("<br>");
        html.append("<span>Regards</span>");
        html.append("<br>");
        html.append("<span>Team " + emailRequest.getProductName() + "</span>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<br>");
        html.append("<p>**This is a system generated mail, please do not reply on this mail.</p>");
        emailRequest.setHtmlEmailBody(html.toString());
        return emailRequest;
    }

}
