package com.mactec.mactax.model.enums;

/**
 * 
 * @author akshaylap
 *
 */
public enum OtpType {

    NONE("NONE"), ONCE_LOGIN_ONCE_REGISTER("ONCE_LOGIN_ONCE_REGISTER"), EVERY_LOGIN_ONCE_REGISTER("EVERY_LOGIN_ONCE_REGISTER");

    private String id;

    private OtpType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
