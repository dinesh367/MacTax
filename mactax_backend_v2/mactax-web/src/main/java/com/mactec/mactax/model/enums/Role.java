package com.mactec.mactax.model.enums;

/**
 * 
 * @author akshayp
 *
 */
public enum Role {

    SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), TAX_CONSULTANT("TAX_CONSULTANT"), TAX_PAYER("TAX_PAYER");

    private String id;

    private Role(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
