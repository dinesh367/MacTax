package com.mactec.mactax.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * @author akshayp
 *
 */
@Data
@Embeddable
public class OrganizationAddress {

    @Size(min = 2, max = 50)
    @NotNull
    @Column
    private String line1;

    @Size(min = 2, max = 50)
    @Column
    private String line2;

    @Size(min = 2, max = 50)
    @NotNull
    @Column
    private String city;

    @Size(min = 2, max = 50)
    @NotNull
    @Column
    private String state;

    @Pattern(regexp = "^[1-9][0-9]{5}$")
    @NotNull
    @Column
    private String pincode;

    @Size(min = 2, max = 50)
    @NotNull
    @Column
    private String country;

}
