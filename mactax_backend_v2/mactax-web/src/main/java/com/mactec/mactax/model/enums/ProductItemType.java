package com.mactec.mactax.model.enums;

/**
 * 
 * @author akshaylap
 *
 */
public enum ProductItemType {

    SERVICE("SERVICE"), GOODS("GOODS");

    private String id;

    private ProductItemType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
