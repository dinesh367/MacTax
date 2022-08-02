package com.mactec.mactax.model.enums;

/**
 * 
 * @author akshaylap
 *
 */
public enum SmsProvider {

    AMAZON_SNS("amazon_sns"), ONLY_INDIA("only_india");

    private String id;

    private SmsProvider(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
