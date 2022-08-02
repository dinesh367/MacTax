package com.mactec.mactax.model.enums;

/**
 * 
 * @author akshaylap
 *
 */
public enum EmailType {

    ACCOUNT_REGISTRATION("account_registration"), ACCOUNT_REGISTRATION_WITH_OTP("account_registration_with_otp"), ACCOUNT_LOGIN(
            "account_login"), PASSWORD_RESET("forgotten_password"), ORDER_CONFIRMATION("order_confirmation"), COMMUNICATION("Communication");

    private String id;

    private EmailType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
